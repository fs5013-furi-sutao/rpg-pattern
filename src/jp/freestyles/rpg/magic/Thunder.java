package jp.freestyles.rpg.magic;

import java.util.concurrent.ThreadLocalRandom;

import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.magic.type.Attackable;
import jp.freestyles.rpg.service.base.IMagicService;

public class Thunder implements IMagic, Attackable {
    
    private static final int CONSUMPTION_MP = 20;

    // 敵に 10 ～ 30 の防御無視ダメージ
    private static final int MIN_DAMAGE =10;
    private static final int MAX_DAMAGE = 30;

    private IMagicService service;

    public Thunder(IMagicService service) {
        this.service = service;
    }

    public void effect() {
        
    }

    public int getDamageValue() {
        return ThreadLocalRandom.current().nextInt(MIN_DAMAGE, MAX_DAMAGE + 1);
    }
}
