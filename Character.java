package rogue;

public class Character {
	protected String name;
	protected int hp;
	protected int mp;
	protected int ap;
	protected int dp;
	protected int maxHP;			//戦闘終了後リカバリー用
	protected int maxMP;

	public Character() {
	}

	//名前取得
	public String getName() {
		return this.name;
	}

	//HP取得
	public int getHP() {
		return this.hp;
	}

	//MP取得
	public int getMP() {
		return this.mp;
	}

	//AP取得
	public int getAP() {
		return this.ap;
	}

	//DP取得
	public int getDP() {
		return this.dp;
	}

	//最大HP取得
	public int getMAXHP() {
		return this.maxHP;
	}

	//最大MP取得
	public int getMAXMP() {
		return this.maxMP;
	}



	//コンバット用メソッド------------------------------------------------------------------------------------------
	public int attacked(Character c) {
		//例　PlayerAのメソッド呼び出し，引数MonsterB
		//BがAに攻撃し，Aの体力が減る．
		this.hp -= c.getAP();
		return c.getAP();
	}

	//攻撃時に使う
	public int atkap() {
		return this.getAP();
	}


	//戦闘終了後,モンスターは呼び出すこと
	//レベルアップ時，プレイヤーは呼び出すこと
	public void recaverHP() {
		this.hp = this.maxHP;
		this.mp = this.maxMP;
	}

}
