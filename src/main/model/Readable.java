package model;

import java.io.IOException;

public interface Readable {
    public void load() throws IOException, ClassNotFoundException;
}
