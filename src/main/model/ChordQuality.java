package model;

public enum ChordQuality {
    MAJ("maj"), MIN("min"), AUG("aug"), DIM("dim");

    private String quality;

    ChordQuality(String s) {
        this.quality = s;
    }

    public String getValue() {
        return quality;
    }
}
