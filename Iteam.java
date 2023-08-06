package rogue;

public class Iteam {
	private String ITEAMtype;
	private String ITEAMname;
	private int ITEAMap;
	private int ITEAMdp;
	private int ITEAMhp;
	private int ITEAMmp;
	private int ITEAMhas;		//所持判定　0：未所持　1：所持

	public Iteam(String type, String name, int ap, int dp, int hp, int mp) {
		this.ITEAMtype = type;
		this.ITEAMname = name;
		this.ITEAMap = ap;
		this.ITEAMdp = dp;
		this.ITEAMhp = hp;
		this.ITEAMmp = mp;
		this.ITEAMhas = 0;
	}



	public int IteamUse(Character c) {
		if(this.ITEAMhas <= 0) {			//所持判定
			Main.console.makeLog("You don't have it.");
			return 0;
		}else {
			switch(this.ITEAMtype) {
			case "WEAPON":							//武器	攻撃力を返す
				return this.ITEAMap;
			case "ARMOR":							//防具	防御力を返す
				return this.ITEAMdp;
			case "USE":								//回復アイテム　回復する
				this.IteamLost();
				if(this.ITEAMhp != 0) {					//HP回復アイテム
					c.hp += this.ITEAMhp;
					if(c.maxHP < c.hp) c.hp = c.maxHP;			//最大HP超過時処理
					Main.console.makeLog("You have used " + this.ITEAMname + ".\n");
					Main.console.makeLog("You Recovered " + this.ITEAMhp + "points.\n");
					return 0;
				}else if(this.ITEAMmp != 0){					//MP回復アイテム（未実装）;
					c.maxMP += this.ITEAMmp;
					if(c.maxMP > c.mp) c.maxMP = c.maxMP;
					return 0;
				}else {
					return 0;								//その他アイテム時
				}
			default:
				Main.console.makeLog("ITEAM ID ERROR.");
				return 0;									//エラー用
			}
		}
	}


	//アイテム入手
	public void IteamGet(int i) {
		this.ITEAMhas += i;
	}

	//アイテム消失
	public void IteamLost() {
		if(this.ITEAMhas > 0) this.ITEAMhas--;
	}

	//アイテム所持判定
	public int getITEAMhas() {
		return this.ITEAMhas;
	}

	//アイテム名を返す
	public String getITEAMName() {
		return this.ITEAMname;
	}


}
