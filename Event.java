package rogue;


public class Event {
	//buttonで中央押し時，下が”E"だったら呼び出し
	//主にアイテム入手系
	//たまに戦闘
	//ステータス成長もあり
	//踏んだ後は”＿”に変更すること
	private int EVENT_NUM = 2;			//イベント総数　増やしたら追加すること




	public void event1(Character c) {
		Main.console.makeLog("You found a clear spring.\n");
		Main.console.makeLog("Your HP and MP have recoverd!\n");
		c.recaverHP();
	}

	public void event2(Character c) {
		int kind = (int)(Math.random()*3);		//WEAPON ARMOR USE の3種類
		int key ;
		switch(kind) {
		case 0:
			key = 0;		//武器　key0-49
			break;
		case 1:
			key = 50;		//防具　key50-99
			break;
		case 2:
			key = 100;		//アイテム　key100-249
			break;
		default:
			key = 255;		//エラー用
			break;
		}
		key += (int)(Math.random()*((Player) c).getFLOOR()+1);
		Iteam tmpI = Main.IM.getIteamClass(key);
		tmpI.IteamGet(1);

		Main.console.makeLog("You found a " + tmpI.getITEAMName() + ".\n");
	}


	public void random_Eve(Character c) {
		int event_id = (int)(Math.random()*EVENT_NUM+1);		//1-EVENT_NUMまで
		switch(event_id) {
		case 1:
			this.event1(c);
			break;
		case 2:
			this.event2(c);
			break;
		default:
			Main.console.makeLog("You couldn't find anything.\n");
			break;
		}

	}
}




