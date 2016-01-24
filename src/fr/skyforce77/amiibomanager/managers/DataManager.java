package fr.skyforce77.amiibomanager.managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import fr.skyforce77.amiibomanager.data.Amiibo;

public class DataManager {
	
    private static HashMap<String, Object> map = new HashMap<String, Object>();
    private static ArrayList<String> perm = new ArrayList<String>();
    private static File directory = null;

    public static Object getValue(String key) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return null;
        }
    }

    public static Object getValue(String key, Object def) {
        if (map.containsKey(key)) {
            return map.get(key);
        } else {
            return def;
        }
    }

    public static void setValue(String key, Object value) {
        map.put(key, value);
        save();
    }

    public static boolean hasPermission(String key) {
        return perm.contains(key);
    }

    public static void addPermission(String key) {
        if (!hasPermission(key)) {
            perm.add(key);
            save();
        }
    }

    public static void removePermission(String key) {
        perm.remove(key);
        save();
    }

    public static void save() {
        try {
            FileOutputStream bos = new FileOutputStream(new File(DataManager.getDirectory(), "database"));
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(map);
            out.writeObject(perm);
            bos.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static void load() {
        File f = new File(DataManager.getDirectory(), "database");
        try {
            if (!f.exists()) {
            	f.getParentFile().mkdirs();
                f.createNewFile();
                save();
                return;
            }
            FileInputStream fis = new FileInputStream(f);
            ObjectInput in = new ObjectInputStream(fis);
            map = (HashMap<String, Object>) in.readObject();
            try {
                perm = (ArrayList<String>) in.readObject();
            } catch (Exception e) {
            }
            fis.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static File getDirectory() {
		if(directory != null) {
			return directory;
		}
		File f = null;
		String OS = System.getProperty("os.name").toUpperCase();
		if (OS.contains("WIN"))
			f = new File(System.getenv("APPDATA"), "/.amiibomanager");
		else if (OS.contains("MAC"))
			f = new File(System.getProperty("user.home") + "/Library/Application Support", "/.amiibomanager");
		else if (OS.contains("NUX"))
			f = new File(System.getProperty("user.home"), "/.amiibomanager");
		else
			f = new File(System.getProperty("user.dir"), "/.amiibomanager");
		directory = f;
		return f;
	}
    
    @SuppressWarnings("unchecked")
	public static Map<String,Amiibo> getAmiiboMap() {
        if (!map.containsKey("amiiboMap")) {
            map.put("amiiboMap", new HashMap<String,Amiibo>());
        }
        return (Map<String,Amiibo>)map.get("amiiboMap");
    }

}
