
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

#define HEADLINK    //��ͷ�������ʵ��


/**************************************************************
*	Struct Define Section
**************************************************************/
//���нṹ
typedef struct node
{
    void* data;       //������ָ��
    struct node *next;//ָ��ǰ������һ���
} Node;

typedef struct queue
{
    Node *front;       //��ͷ
    Node *rear;        //��β
    size_t data_size;  //�������ڴ��С
    int count;
} Queue;


//Status���Ͷ���
typedef enum
{
    FLASE, TRUE
} Status;

//����ö��
enum {
    INT, FLOAT, CHAR
};

/**************************************************************
*	Prototype Declare Section
**************************************************************/

/**
 *  @name        : void InitQueue(Queue *Q)
 *	@description : ��ʼ������
 *	@param		 : ����ָ��Q
 *	@return		 : None
 *  @notice      : None
 */
void InitQueue(Queue *Q);

/**
 *  @name        : void DestoryQueue(Queue *Q)
 *	@description : ���ٶ���
 *	@param		 : ����ָ��Q
 *	@return		 : None
 *  @notice      : None
 */
void DestoryQueue(Queue *Q);

/**
 *  @name        : Status IsEmptyQueue(const Queue *Q)
 *	@description : �������Ƿ�Ϊ��
 *	@param		 : ����ָ��Q
 *	@return		 : ��-TRUE; δ��-FLASE
 *  @notice      : None
 */
Status IsEmptyQueue(const Queue *Q);

/**
 *  @name        : Status GetHeadQueue(Queue *Q, void *e)
 *	@description : �鿴��ͷԪ��
 *	@param		 : ����ָ��Q�����Ԫ��e
 *	@return		 : �ɹ�-TRUE; ʧ��-FLASE
 *  @notice      : �����Ƿ��
 */
Status GetHeadQueue(Queue *Q, void *e);

/**
 *  @name        : int LengthQueue(Queue *Q)
 *	@description : ȷ�����г���
 *	@param		 : ����ָ��Q
 *	@return		 : ���г���count
 *  @notice      : None
 */
int LengthQueue(Queue *Q);

/**
 *  @name        : Status EnQueue(Queue *Q, void *data)
 *	@description : ��Ӳ���
 *	@param		 : ����ָ��Q,�������ָ��data
 *	@return		 : �ɹ�-TRUE; ʧ��-FLASE
 *  @notice      : �����Ƿ����˻�Ϊ��
 */
Status EnQueue(Queue *Q, void *data);

/**
 *  @name        : Status DeQueue(Queue *Q)
 *	@description : ���Ӳ���
 *	@param		 : ����ָ��Q
 *	@return		 : �ɹ�-TRUE; ʧ��-FLASE
 *  @notice      : None
 */
Status DeQueue(Queue *Q);

/**
 *  @name        : void ClearQueue(Queue *Q)
 *	@description : ��ն���
 *	@param		 : ����ָ��Q
 *	@return		 : None
 *  @notice      : None
 */
void ClearQueue(Queue *Q);

/**
 *  @name        : Status TraverseQueue(const Queue *Q, void (*foo)(Node *q))
 *	@description : ������������
 *	@param		 : ����ָ��Q����������ָ��foo
 *	@return		 : None
 *  @notice      : None
 */
Status TraverseQueue(const Queue *Q, void (*foo)(Node *q));

/**
 *  @name        : void select()
 *	@description : �˵�ѡ��
 *	@param		 : None
 *	@return		 : None
 *  @notice      : None
 */

void select();

/**
 *  @name        : void out_char(Node *q)
 *	@description : ����ַ�������
 *	@param		 : �ṹ��ָ��q
 *	@return		 : None
 *  @notice      : None
 */

void out_char(Node *q);

/**
 *  @name        : void out_int(Node *q)
 *	@description : �����������
 *	@param		 : �ṹ��ָ��q
 *	@return		 : None
 *  @notice      : None
 */

void out_int(Node *q);

/**
 *  @name        : void out_float(Node *q)
 *	@description : �������������
 *	@param		 : �ṹ��ָ��q
 *	@return		 : None
 *  @notice      : None
 */

void out_float(Node *q);

/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/
#endif // QUEUE_H_INCLUDED
