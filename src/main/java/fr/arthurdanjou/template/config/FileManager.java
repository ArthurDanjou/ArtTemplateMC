package fr.arthurdanjou.template.config;

import fr.arthurdanjou.template.Main;
import fr.arthurdanjou.template.config.settings.Settings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.CustomClassLoaderConstructor;
import org.yaml.snakeyaml.introspector.BeanAccess;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@AllArgsConstructor
@Getter
public class FileManager {

    private final Main main;
    private final ClassLoader classLoader;

    public void saveResourceAs(String resource, String output) {
        if (resource == null || resource.isEmpty()) {
            throw new IllegalArgumentException("La ressource ne peut pas etre nulle !");
        }
        InputStream inputStream = main.getResource(resource);

        if (inputStream == null) {
            throw new IllegalArgumentException("La ressource " + resource + " n'est pas presente dans le dossier !");
        }

        if (!main.getDataFolder().exists() && !main.getDataFolder().mkdir()) {
            main.getLogger().severe("Permission insuffisante pour creer le dossier");
        }

        File outputFile = new File(main.getDataFolder(), output);

        try {
            if (!outputFile.exists()) {
                main.getLogger().info("La ressource est introuvable, creation en cours !");
                OutputStream outputStream = new FileOutputStream(outputFile);

                byte[] buf = new byte[1024];
                int n;
                while ((n = inputStream.read(buf)) >= 0) {
                    outputStream.write(buf, 0, n);
                }
                outputStream.close();
                inputStream.close();

                if (!outputFile.exists()) {
                    main.getLogger().severe("Impossible de creer le fichier !");
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Settings getSettings() {
        Settings settings = null;

        try (Reader reader = Files.newBufferedReader(ConfigFiles.CONFIG.getFile().toPath(), StandardCharsets.UTF_8)) {
            Yaml yaml = new Yaml(new CustomClassLoaderConstructor(this.getClassLoader()));
            yaml.setBeanAccess(BeanAccess.FIELD);
            
            settings = yaml.loadAs(reader, Settings.class);
            main.getLogger().info("Le fichier de configuration a ete lu correctement !");
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        return settings;
    }

}
