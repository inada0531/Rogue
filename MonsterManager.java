package rogue;

import java.util.ArrayList;

public class MonsterManager {
	public static ArrayList<Monster> MONSTERS = new ArrayList<Monster>();
	//移動成功時　確率でコンバット発生
	//ランダムでListの中から敵を選んで
	public MonsterManager() {
		MONSTERS.add(new MonsterGoblin("Goblin", 30, 0, 10, 0));
		MONSTERS.add(new MonsterGoblin("GoblinSoldier", 40, 0, 30, 0));
		MONSTERS.add(new MonsterGoblin("Kobold", 50, 0, 20, 0));

	}


	public int getSize() {
		return MONSTERS.size();
	}
}
