package rogue;

public class Combat {
	int Mlv = 0;
	private Monster enemy;
	private Player c;
	public Combat() {
		//コンストラクタ

		}


	//戦闘判定 確率で戦闘を行うか判定
	//移動時，毎回呼び出される
	public void engage(Player c) {
		//戦闘判定
		int dice = 10 - c.getFLOOR();				//深くなるほど戦闘発生しやすくなる
		if(dice < 5) dice = 5;
		if((int)(Math.random()*dice) == 0) {
			this.c = c;
			//階層を係数にした計算式　深層ほどListの大きいindexを選びやすくなる
			Mlv = (int) (Math.random()*3);
			if(Main.MM.getSize()-1 < Mlv) {
				Mlv = Main.MM.getSize()-1;
			}
			enemy = MonsterManager.MONSTERS.get(Mlv);
			Main.console.makeLog(this.enemy.name + " appeared!\n");
			Main.console.makeLog("<" + this.enemy.getName() + ">" + " HP : " + this.enemy.getHP() + "\n");

			//カードパネルの切り替え　戦闘用に
			Main.button.combatMode();
		}
	}

	//戦闘ボタンが押されたら呼び出す
	public void attack() {
		int damage = 0;
		damage = this.enemy.attacked(this.c);
		Main.console.makeLog("[Battle Log]You damaged " + this.enemy.getName() + " "+ damage + " points.\n");
		if(this.enemy.getHP() > 0) {
			damage = this.c.attacked(this.enemy);
			Main.console.makeLog("[Battle Log]" + this.enemy.getName() + " damaged you " + damage + " points.\n");
			Main.console.makeLog("<" + this.enemy.getName() + ">" + " HP : " + this.enemy.getHP() + "\n");
		}else {
			Main.console.makeLog("<" + this.enemy.getName() + ">" + " HP : 0\n");
			this.finish();
		}
	}

	//アイテムボタンが押されたら呼び出す
	public void Iteam() {

	}

	//逃走ボタンが押されたら呼び出す
	public void escape() {
		this.finish();
	}

	//戦闘終了メソッド　モンスター討伐時のみ
	//ボタンを押せるようにするメソッドを呼び出す
	public void finish() {
		int tmp = this.enemy.getMAXHP() - this.enemy.getHP();
		Main.console.makeLog("[Battle Result]exp+" + tmp + "\n");
		this.c.addEXP(tmp);
		Main.console.makeLog("");
		this.enemy.recaverHP();
		Main.button.moveMode();
	}
}
