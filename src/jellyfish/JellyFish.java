package jellyfish;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JellyFish {
	//ゲーム回数
	static	final int COUNT = 5;
	//勝負結果人間
	static int yourScore;
	//勝負結果PC
	static int comScore;

	public static void main(String[] args) {
		InitJellyfish();
		PlayJellyfish();
		EndJellyfish();
	}
	//ゲームの初期設定
	public static void InitJellyfish() {
		//結果の初期化
		yourScore = 0;
		comScore = 0;
	}
	public static void PlayJellyfish() {
		String [] janken = {"グー", "チョキ","パー"};
		int i = 0;
		boolean playMode = false;
		while (i < COUNT){
			System.out.println(i+1 + "回戦目 (グー:0 チョキ:1 パー:2を入力)");
			InputStreamReader in = new InputStreamReader(System.in);
			BufferedReader reader = new BufferedReader(in);
			try {
				String line = reader.readLine();
				int you = Integer.parseInt(line);
				if (you<0 || you>2) {
					System.out.println("0～2までの数字を入力してください。");
					continue;
				}
				//comのインチキモード発動チェック
				if (i <= 3 && yourScore == 2) {
					playMode = true;
				} else {
					playMode = false;
				}
				//comのじゃんけん
				int com = ComPlay(playMode,you);
				//じゃんけんの表示
				System.out.println("you:"+janken[you]);
				System.out.println("com:"+janken[com]);
				//判定
				int judg = JudgmentJam(you,com);
				//あいこのチェック
				if (judg != 0) {
					i++;
				}
			} catch(IOException e) {
				System.out.println(e);
			} catch(NumberFormatException e) {
				System.out.println("0～2の数値を入力してください。");
			}
		}
	}
	public static void EndJellyfish() {
		System.out.println("-------------------------------------");
		if (yourScore > comScore) {
			System.out.println("人間の勝ち ");
		} else {
			System.out.println("COMの勝ち ");
		}
	}
	//------------------------------------------
	//COMのじゃんけん処理（インチキモード付）
	//------------------------------------------
	public static int ComPlay(boolean quackMode,int youCode) {
		int comPlay = 0;
		if (quackMode == true) {	//インチキモード
			System.out.println("インチキモード発動");
			comPlay = (youCode - 1 + 3) % 3;
		} else	{
			comPlay = (int)(Math.random()*3);
		}
		return comPlay;
	}

	//---------------------------
	//判定処理
	//---------------------------
	public static int JudgmentJam(int manplayer, int complayer) {
		int judg = (manplayer - complayer + 3) % 3;	//じゃんけんの結果判定方法
		if (judg == 1) {
			comScore++;
			System.out.print("Com 勝ち");
		} else if (judg == 2) {
			yourScore++;
			System.out.print("You 勝ち");
		} else {
			System.out.print("あいこなので再戦");
		}
		System.out.println(" You:" + yourScore + "勝 Com:" + comScore + "勝");
		System.out.println("");
		return judg;
	}
}
