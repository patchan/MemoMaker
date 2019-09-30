package model;

import java.io.IOException;

public interface Writeable {

    // REQUIRES: non-empty memo
    // EFFECTS: saves this memo to a file
    public void save() throws IOException;
}
