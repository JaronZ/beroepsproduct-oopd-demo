module waterworld {
    requires hanyaeger;
    requires annotations;

    exports com.github.hanyaeger.tutorial;

    opens audio;
    opens backgrounds;
    opens sprites;
}
