package rogue;

public class Map {

	//41*23のマップ配列
	//1-300 1-200までが迷路，外周１枠
	//奇数列が壁
	//壁は■
	//通路は＿
	//階段は▼
	//イベントはヨ
	//アイテムは宝
	private static String map[][];
	private int x, y;
	private int EVENT_MAX = 3;					//階層ごとのイベントの最大数　現在は1-3

	//Mapの自動作成
	//引数floor階層によって難易度を変える
	public Map(int floor) {

		//穴掘りアルゴリズム
		this.makedungeon();


	}

	//マップを返す
	public String[][] getMap() {
		return map;
	}
	//指定座標のステータスを返す
	public String getMapEvent(int x, int y) {
		if(x < 0 || y <0 || 40 < x || 22 < y) {
			return "■";
		}else {
			return map[x][y];
		}
	}

	//指定座標を書き換える
	public void changeStatus(int x, int y, String n) {
		if(n == "■" || n == "＿" || n == "ヨ")	map[x][y] = n;
	}

	//通路をランダムに抽出する
	public int[] choiceone() {
		while(true) {
			this.x = (int) (Math.random()*39)+1;		//1-39のうちランダムに決定
			this.y = (int) (Math.random()*21)+1;		//1-21のうちランダムに決定
			if(map[x][y] == "＿" && x%2 == 0 && y%2 == 0) {			//偶数行・列かつ通路ならbreak;
				break;
			}
		}
		int turo[] = new int[2];
		turo[0] = this.x;
		turo[1] = this.y;
		return turo;
	}

	//穴掘りアルゴリズムで迷路を掘る
	private void makedungeon() {
		int dig = 300;							//迷路作成時の穴堀回数
		int way = (int) (Math.random()*4);		//方向変数0-4
		map = new String[41][23];			//map初期化
		for(int i=0;i<41; i++) {				//すべて■で埋める
			for(int j=0; j<23; j++) {
				if(i==0 || j == 0 || i==40 || j==22) {
					map[i][j] = "　";
				}else {
					map[i][j] = "■";
				}
			}
		}

		//スタート地点決定
		while(true) {
			this.x = (int) (Math.random()*39)+1;									//1-199のうちランダムに決定
			this.y = (int) (Math.random()*21)+1;
			if(map[x][y] == "■" && this.x%2 == 0 && this.y%2 == 0) break;		//偶数行・列ならbreak;
		}
		map[x][y] = "＿";

		//方角に従い穴掘りをする
		for(int n=0; n<dig; n++) {
			switch(way) {
			case 0:											//北
				if(map[x-2][y] != "■") {					//掘削失敗
					way = (int) (Math.random()*4);			//方向転換
					choiceone();							//掘削地点変更
				}else {
					map[x-2][y] = "＿";						//掘削成功
					map[x-1][y] = "＿";
					x -= 2;
				} break;
			case 1:											//南
				if(map[x+2][y] != "■") {					//掘削失敗
					way = (int) (Math.random()*4);			//方向転換
					choiceone();							//掘削地点変更
				}else {
					map[x+2][y] = "＿";						//掘削成功
					map[x+1][y] = "＿";
					x += 2;
				} break;
			case 2:											//東
				if(map[x][y+2] != "■") {					//掘削失敗
					way = (int) (Math.random()*4);			//方向転換
					choiceone();							//掘削地点変更
				}else {
					map[x][y+2] = "＿";						//掘削成功
					map[x][y+1] = "＿";
					y += 2;
				} break;
			case 3:											//西
				if(map[x][y-2] != "■") {					//掘削失敗
					way = (int) (Math.random()*4);			//方向転換
					choiceone();							//掘削地点変更
				}else {
					map[x][y-2] = "＿";						//掘削成功
					map[x][y-1] = "＿";
					y -= 2;
				} break;
			}
		}

		//ゴール位置を作る
		int goalXY[] = new int[2];
		goalXY = this.choiceone();
		map[goalXY[0]][goalXY[1]] = "∨";


		//イベントを作る
		int event = (int)(Math.random()*EVENT_MAX+1);			//0-EVENT_MAXまでのイベント発生回数
		int eventXY[] = new int[2];
		for(int i=0; i<event; i++) {
			eventXY = this.choiceone();
			while(map[eventXY[0]][eventXY[1]] == "∨") {
				eventXY = this.choiceone();
			}
			map[eventXY[0]][eventXY[1]] = "ヨ";
		}
	}
}
