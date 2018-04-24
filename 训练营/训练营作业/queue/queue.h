
/**************************************************************
*	Multi-Include-Prevent Section
**************************************************************/
#ifndef QUEUE_H_INCLUDED
#define QUEUE_H_INCLUDED


/**************************************************************
*	Include File Section
**************************************************************/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <conio.h>
#include <ctype.h>

/**************************************************************
*	Macro Define Section
**************************************************************/
#define LEN sizeof(struct node)

#define HEADLINK    //带头结点链表实现


/**************************************************************
*	Struct Define Section
**************************************************************/
//队列结构
typedef struct node
{
    void* data;       //数据域指针
    struct node *next;//指向当前结点的下一结点
} Node;

typedef struct queue
{
    Node *front;       //队头
    Node *rear;        //队尾
    size_t data_size;  //数据域内存大小
    int count;
} Queue;


//Status类型定义
typedef enum
{
    FLASE, TRUE
} Status;

//类型枚举
enum {
    INT, FLOAT, CHAR
};

/**************************************************************
*	Prototype Declare Section
**************************************************************/

/**
 *  @name        : void InitQueue(Queue *Q)
 *	@description : 初始化队列
 *	@param		 : 队列指针Q
 *	@return		 : None
 *  @notice      : None
 */
void InitQueue(Queue *Q);

/**
 *  @name        : void DestoryQueue(Queue *Q)
 *	@description : 销毁队列
 *	@param		 : 队列指针Q
 *	@return		 : None
 *  @notice      : None
 */
void DestoryQueue(Queue *Q);

/**
 *  @name        : Status IsEmptyQueue(const Queue *Q)
 *	@description : 检查队列是否为空
 *	@param		 : 队列指针Q
 *	@return		 : 空-TRUE; 未空-FLASE
 *  @notice      : None
 */
Status IsEmptyQueue(const Queue *Q);

/**
 *  @name        : Status GetHeadQueue(Queue *Q, void *e)
 *	@description : 查看队头元素
 *	@param		 : 队列指针Q，存放元素e
 *	@return		 : 成功-TRUE; 失败-FLASE
 *  @notice      : 队列是否空
 */
Status GetHeadQueue(Queue *Q, void *e);

/**
 *  @name        : int LengthQueue(Queue *Q)
 *	@description : 确定队列长度
 *	@param		 : 队列指针Q
 *	@return		 : 队列长度count
 *  @notice      : None
 */
int LengthQueue(Queue *Q);

/**
 *  @name        : Status EnQueue(Queue *Q, void *data)
 *	@description : 入队操作
 *	@param		 : 队列指针Q,入队数据指针data
 *	@return		 : 成功-TRUE; 失败-FLASE
 *  @notice      : 队列是否满了或为空
 */
Status EnQueue(Queue *Q, void *data);

/**
 *  @name        : Status DeQueue(Queue *Q)
 *	@description : 出队操作
 *	@param		 : 队列指针Q
 *	@return		 : 成功-TRUE; 失败-FLASE
 *  @notice      : None
 */
Status DeQueue(Queue *Q);

/**
 *  @name        : void ClearQueue(Queue *Q)
 *	@description : 清空队列
 *	@param		 : 队列指针Q
 *	@return		 : None
 *  @notice      : None
 */
void ClearQueue(Queue *Q);

/**
 *  @name        : Status TraverseQueue(const Queue *Q, void (*foo)(Node *q))
 *	@description : 遍历函数操作
 *	@param		 : 队列指针Q，操作函数指针foo
 *	@return		 : None
 *  @notice      : None
 */
Status TraverseQueue(const Queue *Q, void (*foo)(Node *q));

/**
 *  @name        : void select()
 *	@description : 菜单选择
 *	@param		 : None
 *	@return		 : None
 *  @notice      : None
 */

void select();

/**
 *  @name        : void out_char(Node *q)
 *	@description : 输出字符型数据
 *	@param		 : 结构体指针q
 *	@return		 : None
 *  @notice      : None
 */

void out_char(Node *q);

/**
 *  @name        : void out_int(Node *q)
 *	@description : 输出整型数据
 *	@param		 : 结构体指针q
 *	@return		 : None
 *  @notice      : None
 */

void out_int(Node *q);

/**
 *  @name        : void out_float(Node *q)
 *	@description : 输出浮点型数据
 *	@param		 : 结构体指针q
 *	@return		 : None
 *  @notice      : None
 */

void out_float(Node *q);

/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/
#endif // QUEUE_H_INCLUDED
