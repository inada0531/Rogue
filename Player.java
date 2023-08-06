package rogue;

public class Player extends Character{
	private int hung;			//空腹度
	private int floor;			//現在探索中の階層
	private int exp;
	private int maxEXP;
	private int charX;
	private int charY;
	private int equipWeponNumber;
	private int equipArmorNumber;

	public Player(int startx, int starty) {
		this.name = "you";
		this.hp = (int) (200 + Math.random()*10);
		this.hung = 100;
		this.mp = 50;
		this.ap = 20;
		this.dp = 1;
		this.floor = 1;
		this.exp = 0;
		this.maxEXP = 100;
		this.charX = startx;
		this.charY = starty;
		this.maxHP = this.hp;
		this.maxMP = this.mp;
		this.equipWeponNumber = 0;			//初期装備key->Bare_Hands
		this.equipArmorNumber = 50;			//初期装備key->Leather_Armor
	}

	//キャラの座標を取得
	public int[] getCharXY() {
		int XY[] = {this.charX, this.charY};
		return XY;
	}

	//座標をセットする
	public void setCharXY(int x, int y){
		this.charX = x;
		this.charY = y;
	}

	//経験値取得
	public int getEXP() {
		return this.exp;
	}

	//経験値最大値取得
	public int getMAXEXP() {
		return this.maxEXP;
	}

	//経験値加算
	public void addEXP(int x) {
		this.exp += x;
		if(this.maxEXP < this.exp) {				//レベルアップ時の処理
			String msg;
			double tmp;
			msg = "[Level Up!]";
			tmp = (Math.random()*this.maxHP/10)+1;
			this.maxHP += (int)tmp;
			msg += "  HP↑" + (int)tmp;
			tmp = (Math.random()*this.maxMP/10)+1;
			this.maxMP += (int)tmp;
			msg += "  MP↑" + (int)tmp;
			if((int)(Math.random()*2) == 0) {
				this.ap++;
				msg += "  AP↑1\n";
			}else {
				this.dp++;
				msg += "  DP↑1\n";
			}
			this.maxEXP += (int)(this.maxEXP/100) * 10;		//現在地の10％切り捨てを加算
			this.exp = 0;
			this.hp = this.maxHP;								//全回復処理
			this.mp = this.maxMP;
			Main.console.makeLog(msg);
		}
	}

	//空腹度取得
	public int getHUNG() {
		return this.hung;
	}

	//階層取得
	public int getFLOOR() {
		return this.floor;
	}

	//階層追加
	public void addFLOOR() {
		this.floor++;
	}

	//ステータス取得
	public int[] getStatus() {
		int status[] = new int[3];
		status[0] = this.hp;
		status[1] = this.mp;
		status[2] = this.ap;
		status[3] = this.hung;
		status[4] = this.floor;
		return status;
	}

	//装備する:武器
	public void equipW(int key) {
		this.equipWeponNumber = key;
	}

	//装備する:防具
	public void equipA(int key) {
		this.equipArmorNumber = key;
	}



	//移動
	public void move(int way) {
		switch(way) {
		case 1 :
			if(Main.DUNGEON.getMapEvent(this.charX, this.charY-1) != "■") {
				this.charY--;
				Main.map.rewriteMap(this.charX, this.charY);
				Main.CM.engage(this);
			}
			break;
		case 2 :
			if(Main.DUNGEON.getMapEvent(this.charX, this.charY+1) != "■") {
				this.charY++;
				Main.map.rewriteMap(this.charX, this.charY);
				Main.CM.engage(this);
			}
			break;
		case 3 :
			if(Main.DUNGEON.getMapEvent(this.charX+1, this.charY) != "■") {
				this.charX++;
				Main.map.rewriteMap(this.charX, this.charY);
				Main.CM.engage(this);
			}
			break;
		case 4 :
			if(Main.DUNGEON.getMapEvent(this.charX-1, this.charY) != "■") {
				this.charX--;
				Main.map.rewriteMap(this.charX, this.charY);
				Main.CM.engage(this);
			}
			break;
		default :
			System.out.println("WAY ERROR!");
			break;
		}
	}

	//コンバット用メソッド

	//モンスターに攻撃された時
	@Override
	public int attacked(Character c) {
		int Adp = Main.IM.getIteamClass(this.equipArmorNumber).IteamUse(this);		//装備防御力
		double dmgcut = (1 + ((double)this.dp/100)) * ((double)this.dp + Math.random()*this.dp%10);
		int dmg = (int)(c.atkap() - dmgcut);
		if(dmg < c.atkap()/10) dmg = c.atkap()/10;		//最低保証ダメージ1/10
		if(this.hp <= 0) {
			//GameOverメッセージ
			//ウィンドウを閉じるメソッド---------------------------------------------------------------------------
			System.out.println("Debag : GameOver");
		}
		this.hp -= dmg - Adp;
		return dmg - Adp;
	}

	//モンスターにダメージを与える時に参照される値
	@Override
	public int atkap() {
		//基礎攻撃力＋装備の攻撃力x(1~xまでランダム)
		int Wap = Main.IM.getIteamClass(this.equipWeponNumber).IteamUse(this);			//装備攻撃力
		return (int)(this.ap*Math.random()) + (int)(Math.random()*Wap) + 1;
	}




}
