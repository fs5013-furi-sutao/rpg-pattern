package jp.freestyles.rpg.injection.magic;

import jp.freestyles.rpg.injection.base.IMagicServiceInjector;
import jp.freestyles.rpg.magic.Thunder;
import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.service.StandardMagicService;

public class ThunderInjector implements IMagicServiceInjector {

    public IMagic getMagic() {
        return new Thunder(new StandardMagicService());
    }
}
