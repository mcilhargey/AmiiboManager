package fr.skyforce77.amiibomanager.data;

public enum AmiiboSeries {

	SUPER_SMASH_BROS((byte)0x00),
	SUPER_MARIO((byte)0x01),
	CHIBI_ROBO_ZIP_LASH((byte)0x02),
	YOSHIS_WOOLLY_WORLD((byte)0x03),
	SPLATOON((byte)0x04),
	ANIMAL_CROSSING((byte)0x05),
	ANNIVERSARY_30TH((byte)0x06),
	SKYLANDERS_SUPERCHARGERS((byte)0x07),
	SHOVEL_KNIGHT((byte)0x0a),
	
	UNKNOWN((byte)0x00);
	
	private byte code;
	
	AmiiboSeries(byte code) {
		this.code = code;
	}
	
	public byte getCode() {
		return code;
	}
	
	private void setCode(byte code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		if(this == UNKNOWN) {
			return "UNKNOWN (0x"+String.format("%02x",code)+")";
		} else {
			return this.name();
		}
	}
	
	public static AmiiboSeries fromCode(byte code) {
		for(AmiiboSeries type: values()) {
			if(type.getCode() == code) {
				return type;
			}
		}
		UNKNOWN.setCode(code);
		return UNKNOWN;
	}
	
}
