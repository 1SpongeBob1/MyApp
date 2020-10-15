using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using Random = System.Random;

public class GameController : MonoBehaviour
{

    private int[,] cardsArray = new int[4, 5];      //4行5列，存放“数字牌”。
    private int firstNumCard = -1;
    private int secondNumCard = -2;
    private Button btnFirstNumCard;
    private Button btnSecondNumCard;
    private bool isFirstClick = true;


    // Start is called before the first frame update
    void Start()
    {
        //初始化二维数组，准备游戏数据。
        StartPrepareGameData();
        //使用协程进行轮询检测。
        StartCoroutine(JudgePoker(1.5F));
    }

    // Update is called once per frame
    void Update()
    {
        
    }

    
    IEnumerator JudgePoker(float floTime)
    {
        while (true)
        {
            yield return new WaitForSeconds(floTime);
            JudgePokerEquals(btnFirstNumCard, btnSecondNumCard, ref firstNumCard, ref secondNumCard);
        }
    }

    /**
     * 判断牌是否相同。
     * 功能：
     *      相同会变灰。
     *      不同牌会再反转回去。
     * 
     * 
     */
    private void JudgePokerEquals(Button btnFirstNumCard, Button btnSecondNumCard, ref int firstNumCard, ref int secondNumCard)
    {
        if (firstNumCard == secondNumCard)
        {
            btnFirstNumCard.GetComponent<Image>().overrideSprite = Resources.Load("Sprites/bg", typeof(Sprite)) as Sprite;
            btnSecondNumCard.GetComponent<Image>().overrideSprite = Resources.Load("Sprites/bg", typeof(Sprite)) as Sprite;
            btnFirstNumCard.onClick.AddListener(TaskonClick);
            btnSecondNumCard.onClick.AddListener(TaskonClick);

        }
    }

    void TaskonClick()
    {
        return;
    }

    private void StartPrepareGameData()
    {
        //得到10组乱序数字牌
        ArrayList list = CreateRandomDoublePlayCard();
        //获取迭代器接口
        IEnumerator enumerator = list.GetEnumerator();

        for (int i = 0; i < 4; i++)
        { 
            for (int j = 0; j < 5; j ++)
            {
                if (enumerator.MoveNext())
                {
                    cardsArray[i, j] = (int)enumerator.Current;
                }
            }
        }

        //添加“初始牌”的背景
        button1_1.GetComponent<Image>().overrideSprite = Resources.Load("Resources/1", typeof(Sprite)) as Sprite;
        button1_2.GetComponent<Image>().overrideSprite = Resources.Load("Resources/1", typeof(Sprite)) as Sprite;
        
    }

    private ArrayList CreateRandomDoublePlayCard()
    {
        ArrayList list = new ArrayList();
        //产生10对数字牌
        for (int i = 0; i < 10; i ++)
        {
            list.Add(GetRandom(1, 9));
        }
        //得到成对出现
        for (int i = 0; i < 10; i ++)
        {
            int temp = (int)list[i];
            list.Add(temp);
        }
        //要求乱序排列
        for (int i = 0; i < list.Count; i ++)
        {
            int randomPosition = GetRandom(1, list.Count);
            int temp = (int)list[i];
            list[i] = list[randomPosition];
            list[randomPosition] = temp;
        }

        return list;
    }

    private void ProccessUserClick(Button butObj, int rowsNumber, int columnsNumber)
    {
        //不能点击第三张牌
        if (firstNumCard != -1 && secondNumCard != -2)
        {
            return;
        }

        //第一张牌
        if (isFirstClick)
        {
            isFirstClick = false;
            firstNumCard = cardsArray[rowsNumber, columnsNumber];
            //显示对应的图片
            DisplayPokerCardByNumber(butObj, firstNumCard);
            btnFirstNumCard = butObj;
        }

        //第二张牌
        else {
            isFirstClick = true;
            secondNumCard = cardsArray[rowsNumber, columnsNumber];
            //显示对应的图片
            DisplayPokerCardByNumber(butObj, secondNumCard);
            btnSecondNumCard = butObj;
        }
    }

    private void DisplayPokerCardByNumber(Button butObj, int firstNumCard)
    {
        throw new NotImplementedException();
    }

    private int GetRandom(int v1, int v2)
    {
        return new Random().Next(v1, v2);
    }
}
