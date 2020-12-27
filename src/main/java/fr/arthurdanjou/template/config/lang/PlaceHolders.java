package fr.arthurdanjou.template.config.lang;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum PlaceHolders {

    PLAYER("player"),
    SLOTS("slots"),
    COUNTDOWN("countdown"),

    ;

    private final String placeholder;

    public String getPlaceholder() {
        return "{" + placeholder + "}";
    }
}
