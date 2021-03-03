package jp.freestyles.rpg.magic;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.service.base.IMagicService;

public class Poison implements IMagic, Attackable {
    
    private static final int CONSUMPTION_MP = 10;

    private IMagicService service;

    public void effect() {
        
    }

    public int getDamageValue() {
        return 0;
    }
}
