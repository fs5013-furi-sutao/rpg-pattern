# RPG pattern

## 仕様

### キャラクタ（Player）

- 戦士（Fighter）, 魔法使い（Wizard）, 僧侶（Priest）を
  「職業別パラメータ表」にしたがったパラメータ（Status）で生成する

- 各キャラクタが使用できる魔法は「魔法一覧」にしたがう
- MP があれば魔法をランダムで使用して攻撃し、MP がない場合は通常攻撃を行う
- HP が減っていれば「ヒール」を使用し、減ってなければ通常攻撃を行う

### 状態異常

- 「パラライズ」「ポイズン」の魔法が成功した場合は、毒、麻痺などの状態異常にする

### 素早さで攻撃順が変わる

- AGI が高い方が先に攻撃する

## 職業別パラメータ表

| 職業     | HP         | MP       | STR       | DEF       | LUCK     | AGI      |
| :------- | :--------- | :------- | :-------- | :-------- | :------- | :------- |
| 戦士     | 100 ～ 300 | 0        | 30 ～ 100 | 30 ～ 100 | 1 ～ 100 | 1 ～ 50  |
| 魔法使い | 50 ～ 150  | 30 ～ 80 | 1 ～ 50   | 1 ～ 50   | 1 ～ 100 | 20 ～ 60 |
| 僧侶     | 80 ～ 200  | 20 ～ 50 | 10 ～ 70  | 10 ～ 70  | 1 ～ 100 | 20 ～ 60 |

## 魔法一覧

| 名称       | 職業     | 消費 MP | 種別     | 効果                                                      |
| :--------- | :------- | :------ | :------- | :-------------------------------------------------------- |
| ファイア   | 魔法使い | 10      | 攻撃魔法 | 敵に 10 ～ 30 の防御無視ダメージ                          |
| サンダー   | 魔法使い | 20      | 攻撃魔法 | 敵に 10 ～ 30 の防御無視ダメージ                          |
| ヒール     | 僧侶     | 20      | 回復魔法 | HP を 50 回復                                             |
| パラライズ | 僧侶     | 10      | 攻撃魔法 | 麻痺の効果を与える <br /> 麻痺：20% の確率で麻痺で行動不能 |
| ポイズン   | 僧侶     | 10      | 攻撃魔法 | 毒状態にする <br /> 毒：毎ターン 20 のダメージを受ける  <br /> 40% の確率で毒状態を抜けられる  |
