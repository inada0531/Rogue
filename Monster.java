package rogue;

public class Monster extends Character{

	public Monster(String name, int hp, int mp, int ap, int dp) {
		this.name = name;
		this.hp = hp;
		this.mp = mp;
		this.ap = ap;
		this.dp = dp;
		this.maxHP = this.hp;
		this.maxMP = this.mp;
	}

	//攻撃されたとき
	@Override
	public int attacked(Character c) {
		this.hp -= c.atkap();
		return c.atkap();
	}

	//攻撃する時に参照される値
	@Override
	public int atkap() {
		//スキル発動率
		int skilluse = this.ap * (1-(this.hp/this.maxHP));
		//スキルによる追加打撃
		int skilldmg = 0;
		if((int)(Math.random()*100)+1 < skilluse) {			//HPが減るほどスキルが発動しやすくなる
			skilldmg = 0;										//ここにskillクラス入れる
			//skilldmg = Main.SM.getSkill(this);				//引数でメソッドを分岐・使うスキルを選択させる
		}
		return (int)(Math.random()*this.ap)+ 1 + skilldmg;
	}


}
