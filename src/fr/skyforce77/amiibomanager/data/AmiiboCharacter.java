package fr.skyforce77.amiibomanager.data;

public enum AmiiboCharacter {

	//Mario
	MARIO(new byte[]{0x00, 0x00}),
	LUIGI(new byte[]{0x00, 0x01}),
	PEACH(new byte[]{0x00, 0x02}),
	YOSHI(new byte[]{0x0, 0x3}),
	ROSALINA(new byte[]{0x0, 0x4}),
	BOWSER(new byte[]{0x00, 0x05}),
	BOWSER_JUNIOR(new byte[]{0x00, 0x06}),
	WARIO(new byte[]{0x00, 0x06}),
	DONKEY_KONG(new byte[]{0x00, 0x08}),
	DIDDY_KONG(new byte[]{0x00, 0x09}),
	TOAD(new byte[]{0x0a, 0x02}),
	
	//The Legend Of Zelda
	LINK(new byte[]{0x01, 0x00}),
	ZELDA(new byte[]{0x01, 0x01}),
	GANONDORF(new byte[]{0x01, 0x02}),
	
	//Animal Crossing
	VILLAGER(new byte[]{0x01, (byte)0x80}),
	
	//Star Fox
	FOX(new byte[]{0x05, (byte)0x80}),
	
	//Metroid
	SAMUS(new byte[]{0x05, (byte)0xc0}),
	
	//F-Zero
	CAPTAIN_FALCON(new byte[]{0x06, 0x00}),
	
	//Pikmin
	OLIMAR(new byte[]{0x06, 0x40}),
	
	//Punch Out
	LITTLE_MAC(new byte[]{0x06, (byte)0xc0}),
	
	//Wii Fit?
	WII_FIT_TRAINER(new byte[]{0x07, 0x00}),
	
	//Kid Icarus
	PIT(new byte[]{0x07, 0x40}),
	DARK_PIT(new byte[]{0x07, 0x41}),
	PALUTENA(new byte[]{0x07, 0x42}),
	
	//Retro
	MR_GAME_AND_WATCH(new byte[]{0x07, (byte)0x80}),
	ROB_ROBOTIC_OPERATING_BUDDY(new byte[]{0x07, (byte)0x81}),
	DUCK_HUNT(new byte[]{0x07, (byte)0x82}),
	
	//Splatoon
	INKLING(new byte[]{0x08, 0x00}),
	
	//Pokémon
	CHARIZARD(new byte[]{0x19, 0x06}),
	PIKACHU(new byte[]{0x19, 0x19}),
	JIGGLYPUFF(new byte[]{0x19, 0x27}),
	MEWTWO(new byte[]{0x19, (byte)0x96}),
	LUCARIO(new byte[]{0x1a, (byte)0xc0}),
	GRENINJA(new byte[]{0x1b, (byte)0x92}),
	
	//Kirby
	KIRBY(new byte[]{0x1f, 0x00}),
	META_KNIGHT(new byte[]{0x1f, 0x01}),
	KING_DEDEDE(new byte[]{0x1f, 0x02}),
		
	//Fire Emblem
	MARTH(new byte[]{0x21, 0x00}),
	IKE(new byte[]{0x21, 0x01}),
	LUCINA(new byte[]{0x21, 0x02}),
	ROBIN(new byte[]{0x21, 0x03}),
	
	//Xenoblade
	SHULK(new byte[]{0x22, (byte)0x40}),
	
	//Mother
	NESS(new byte[]{0x22, (byte)0x80}),
	
	//Chibi Robo
	CHIBI_ROBO(new byte[]{0x22, (byte)0xc0}),
	
	//Sonic
	SONIC(new byte[]{0x32, 0x00}),
	
	//Pac-Man
	PAC_MAN(new byte[]{0x33, 0x40}),
	
	//Megaman
	MEGAMAN(new byte[]{0x34, (byte)0x80}),
	
	//Shovel Knight
	SHOVEL_KNIGHT(new byte[]{0x35, (byte)0xc0}),
	
	//Mii
	MII(new byte[]{0x07, (byte)0xc0}),
	
	UNKNOWN(null);
	
	private byte[] code;
	
	AmiiboCharacter(byte[] code) {
		this.code = code;
	}
	
	public byte[] getCode() {
		return code;
	}
	
	private void setCode(byte[] code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		if(this == UNKNOWN) {
			return "UNKNOWN (0x"+String.format("%02x",code[0])+", 0x"+String.format("%02x",code[1])+")";
		} else {
			return this.name();
		}
	}
	
	public static AmiiboCharacter fromPage(byte[] page) {
		for(AmiiboCharacter type: values()) {
			if(type.getCode() != null && type.getCode()[0] == page[0] && type.getCode()[1] == page[1]) {
				return type;
			}
		}
		UNKNOWN.setCode(page);
		return UNKNOWN;
	}
	
}
