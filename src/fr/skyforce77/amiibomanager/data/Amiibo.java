package fr.skyforce77.amiibomanager.data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import fr.skyforce77.amiibomanager.utils.Basics;

public class Amiibo implements Serializable{
	
	private static final long serialVersionUID = -346646721963204125L;

	private byte[] encData;
	private byte[] decData;
	
	public Amiibo(byte[] encData, byte[] decData) {		
		this.encData = encData;
		this.decData = decData;
	}
	
	public Amiibo(byte[] data, boolean encrypted) {		
		if(encrypted) {
			this.encData = data;
			this.decrypt();
		} else {
			this.decData = data;
			this.encrypt();
		}
	}
	
	public AmiiboSeries getSeries() {
		return AmiiboSeries.fromCode(getPage(22, true)[2]);
	}
	
	public AmiiboCharacter getCharacter() {
		return AmiiboCharacter.fromPage(getPage(21, true));
	}
	
	public AmiiboCharacterFigure getCharacterFigure() {
		return AmiiboCharacterFigure.fromPage(getPage(21, true));
	}

	public byte[] getData(boolean encrypted) {
		return encrypted ? encData : decData;
	}
	
	public String getUUID() {
		byte[] uuid = new byte[8];
		System.arraycopy(encData, 0, uuid, 0, uuid.length);
		return Basics.toHexadecimalString(uuid);
	}
	
	public byte[] getRawUUID() {
		byte[] uuid = new byte[8];
		System.arraycopy(encData, 0, uuid, 0, uuid.length);
		return uuid;
	}
	
	public String getName() {
		byte[] nameBytes = new byte[20];
		System.arraycopy(decData, 56, nameBytes, 0, nameBytes.length);
		nameBytes = Basics.toValidString(nameBytes);
		return new String(nameBytes);
	}
	
	public String getUser() {
		byte[] nameBytes = new byte[20];
		System.arraycopy(decData, 102, nameBytes, 0, nameBytes.length);
		nameBytes = Basics.toValidString(nameBytes);
		return new String(nameBytes);
	}
	
	public String dump(boolean encrypted) {
		byte[] data = encrypted ? encData : decData;
		StringBuilder dump = new StringBuilder();
		for(int i = 0; i < data.length; i++) {
			dump.append(i);
			dump.append(": ");
			dump.append(Integer.toHexString(data[i] & 0xFF));
			dump.append('\n');
		}
		return dump.toString();
	}
	
	public String dump(int pageId, boolean encrypted) {
		byte[] page = getPage(pageId, encrypted);
		StringBuilder dump = new StringBuilder();
		for(int i = 0; i < page.length; i++) {
			dump.append(i);
			dump.append(": ");
			dump.append(Integer.toHexString(page[i] & 0xFF));
			dump.append('\n');
		}
		return dump.toString();
	}
	
	public byte[] getPage(int pageId, boolean encrypted) {
		byte[] page = new byte[4];
		System.arraycopy(getData(encrypted), 4*pageId, page, 0, page.length);
		return page;
	}
	
	private void decrypt() {
		//removed due to copyright issues
	}
	
	private void encrypt() {
		//removed due to copyright issues
	}
	
	public static Amiibo fromDumps(File enc, File dec) {
		byte[] encData = new byte[540];
		byte[] decData = new byte[540];
		try {
			BufferedInputStream bfi = new BufferedInputStream(new FileInputStream(enc));
			bfi.read(encData);
			bfi.close();
			bfi = new BufferedInputStream(new FileInputStream(dec));
			bfi.read(decData);
			bfi.close();
			return new Amiibo(encData, decData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Amiibo fromRawDump(File f) {
		try {
			BufferedInputStream bfi = new BufferedInputStream(new FileInputStream(f));
			byte[] data = new byte[540];
			bfi.read(data);
			bfi.close();
			return new Amiibo(data, true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Amiibo fromDecryptedDump(File f) {
		try {
			BufferedInputStream bfi = new BufferedInputStream(new FileInputStream(f));
			byte[] data = new byte[540];
			bfi.read(data);
			bfi.close();
			return new Amiibo(data, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
