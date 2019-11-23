package model;

public enum ChordQuality {
    MAJ("maj"), MIN("min"), AUG("aug"), DIM("dim");

    private String quality;

    ChordQuality(String s) {
        this.quality = s;
    }

    // EFFECTS: returns the string quality value
    public String getValue() {
        return quality;
    }
}
