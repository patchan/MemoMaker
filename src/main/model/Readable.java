package model;

import java.io.IOException;

public interface Readable {

    // REQUIRES: a file to load containing a Memo object
    // EFFECTS: loads a memo into memory
    public void load() throws IOException, ClassNotFoundException;
}
