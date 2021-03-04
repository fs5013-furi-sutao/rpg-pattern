package jp.freestyles.rpg.magic.config;

public enum MagicConfig {

    FIRE("ファイア"), 
    HEAL("ヒール"), 
    PARALYZE("パラライズ"), 
    POISON("ポイズン"), 
    THUNDER("サンダー"),;

    private final String name;

    private MagicConfig(String name) {
        this.name = name;
    }

	public String outName() {
		return this.name;
	}

}
