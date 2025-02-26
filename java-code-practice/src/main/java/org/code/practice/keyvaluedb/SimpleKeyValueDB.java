package org.code.practice.keyvaluedb;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SimpleKeyValueDB {

    private static final String FILENAME = "dbFile.txt";
    public boolean storeRecord(String key, String value) throws IOException {
        Path filePath = getFilePath();
        storeRecordInFile(filePath, key, value);
        return true;
    }

    private void storeRecordInFile(Path filePath, String key, String value) throws IOException {
        assert (Files.exists(filePath));
        System.out.println(filePath.toAbsolutePath());
        Files.write(filePath, (key+"#~#"+value+System.lineSeparator()).getBytes(), StandardOpenOption.APPEND);
    }

    public String getValue(String key) throws IOException {
        Path filePath = getFilePath();
        String value = Files.lines(filePath).map(r -> getValueFromRecord(key, r)).filter(s -> !s.equals(""))
                .reduce(null,(first,second) -> second);
        return value;
    }

    private String[] getKeyValuePair(String record){
        return record.split("#~#");
    }

    private String getValueFromRecord(String key, String record){
        String[] keyValue = getKeyValuePair(record);
        if(keyValue[0].equals(key)){
            return keyValue[1];
        }
        return "";
    }

    private Path getFilePath() throws IOException {
        Path filePath = Paths.get(FILENAME);
        if(!Files.exists(filePath)){
            Files.createFile(filePath);
            Files.write(filePath, ("KEY#~#VALUE"+System.lineSeparator()).getBytes());
        }
        return filePath;
    }

    public static void main(String[] args) throws IOException {

        SimpleKeyValueDB db = new SimpleKeyValueDB();

        db.storeRecord("test","first");
        db.storeRecord("test2","second");
        db.storeRecord("test3", "Third");
        db.storeRecord("test", "finalTT");
        db.storeRecord("test2", "Third3");
        System.out.println(db.getValue("test2"));
    }

}
