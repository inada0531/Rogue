package rogue;

import java.util.HashMap;

public class IteamManager {
	private HashMap<Integer,Iteam> list = new HashMap<Integer,Iteam>();
	private HashMap<String, Iteam> name_list = new HashMap<String, Iteam>();

	public IteamManager() {
		//HashキーとIteamキーは同じ
		//0-49 武器key					//本当は武器ごとにクラス作りたかった...
		//50-99 防具key
		//100- アイテムkey

		for(int i=0; i<255; i++) {		//初期化
			this.list.put(i, new Iteam("USE", "DUMMY", 0, 0, 0, 0));
		}

		list.put(0, new Iteam("WEAPON", "Bare_Hands", 0, 0, 0, 0));
		list.put(1, new Iteam("WEAPON", "Bronze_Sword", 3, 0, 0, 0));
		list.put(2, new Iteam("WEAPON", "Iron_Sword", 5, 0, 0, 0));
		list.put(3, new Iteam("WEAPON", "Silver_Sword", 8, 0, 0, 0));
		list.put(4, new Iteam("WEAPON", "Orcish_Sword", 10, 0, 0, 0));
		list.put(5, new Iteam("WEAPON", "Damascus_Sword", 15, 0, 0, 0));
		list.put(6, new Iteam("WEAPON", "Two_Handed_Sword", 35, 0, 0, 0));
		list.put(7, new Iteam("WEAPON", "Silent_Sword", 20, 0, 0, 0));

		list.put(50, new Iteam("ARMOR", "Leather_Armor", 5, 0, 0, 0));
		list.put(51, new Iteam("ARMOR", "Bone_Armor", 10, 0, 0, 0));
		list.put(52, new Iteam("ARMOR", "Hard_Leather_Armor", 20, 0, 0, 0));
		list.put(53, new Iteam("ARMOR", "Orcish_Armor", 25, 0, 0, 0));
		list.put(54, new Iteam("ARMOR", "Iron_Armor", 15, 0, 0, 0));
		list.put(55, new Iteam("ARMOR", "Chest_Plate", 22, 0, 0, 0));
		list.put(56, new Iteam("ARMOR", "Breast_Armor", 18, 0, 0, 0));
		list.put(57, new Iteam("ARMOR", "Silent_Armor", 30, 0, 0, 0));

		list.put(101, new Iteam("USE", "Red_Portion", 0, 0, 50, 0));
		list.put(102, new Iteam("USE", "Orenge_Portion", 0, 0, 80, 0));
		list.put(103, new Iteam("USE", "Clear_Portion", 0, 0, 1500, 0));

		list.put(254, new Iteam("USE", "DUMMY", 0, 0, 0, 0));


		//初期アイテムの所持
		this.list.get(0).IteamGet(1);
		this.list.get(50).IteamGet(1);
		this.list.get(101).IteamGet(10);

		//アイテム名リストの初期化
		for(int i=0; i<255; i++) {
			this.name_list.put(this.list.get(i).getITEAMName(), this.list.get(i));
		}
	}

	public Iteam getIteamClass(int key) {		//キーから装備orアイテムを指定する
		if(this.list.get(key) == null) {		//割当されてないアイテムを参照した場合
			System.out.println("[DEBUG]:Dummy iteam was refarenced.");
			return this.list.get(255);
		}
		return this.list.get(key);

	}

	//アイテム一覧を渡す
	public HashMap<Integer, Iteam> getIteamMenu() {
		return this.list;
	}

	//渡されたアイテム名からIteam型を返す
	public Iteam getIteam(String name) {
		return this.name_list.get(name);
	}

}
