package jp.freestyles.rpg.injection.magic;

import jp.freestyles.rpg.injection.base.IMagicServiceInjector;
import jp.freestyles.rpg.magic.Paralyze;
import jp.freestyles.rpg.magic.base.IMagic;
import jp.freestyles.rpg.service.StandardMagicService;

public class ParalyzeInjector implements IMagicServiceInjector {

    public IMagic getMagic() {
        return new Paralyze(new StandardMagicService());
    }
}
