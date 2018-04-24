#include "queue.h"

extern int flag;
extern Queue Q;

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
void InitQueue(Queue *Q){
	Q->front = NULL;
	Q->rear = NULL;
	Q->count = 0;
	Q->data_size = 0;
}

/**
 *  @name        : void DestoryQueue(Queue *Q)
 *	@description : 销毁队列
 *	@param		 : 队列指针Q
 *	@return		 : None
 *  @notice      : None
 */
void DestoryQueue(Queue *Q){
	Node *p = NULL;
	while(Q->front != NULL){
		p = Q->front->next;
		free(Q->front);
		Q->front = p;
	}
	Q->count = 0;
	Q->front = Q->rear = NULL;
}

/**
 *  @name        : Status IsEmptyQueue(const Queue *Q)
 *	@description : 检查队列是否为空
 *	@param		 : 队列指针Q
 *	@return		 : 空-TRUE; 未空-FLASE
 *  @notice      : None
 */
Status IsEmptyQueue(const Queue *Q){
	if(Q->front == NULL){
		return TRUE;
	}else{
		return FLASE;
	}
}

/**
 *  @name        : Status GetHeadQueue(Queue *Q, void *e)
 *	@description : 查看队头元素
 *	@param		 : 队列指针Q，存放元素e
 *	@return		 : 成功-TRUE; 失败-FLASE
 *  @notice      : 队列是否空
 */
Status GetHeadQueue(Queue *Q, void *e){
	if (Q->front != NULL) {
		memcpy(e, Q->front->data, Q->data_size);
		return TRUE;
	}
	return FLASE;
}

/**
 *  @name        : int LengthQueue(Queue *Q)
 *	@description : 确定队列长度
 *	@param		 : 队列指针Q
 *	@return		 : 队列长度count
 *  @notice      : None
 */
int LengthQueue(Queue *Q){
	return Q->count;
}

/**
 *  @name        : Status EnQueue(Queue *Q, void *data)
 *	@description : 入队操作
 *	@param		 : 队列指针Q,入队数据指针data
 *	@return		 : 成功-TRUE; 失败-FLASE
 *  @notice      : 队列是否满了或为空
 */
Status EnQueue(Queue *Q, void *data){
	Node *node = (Node*)malloc(sizeof(Node));
	node->next = NULL;
	node->data = data;

	if(Q->rear == NULL){
		Q->front = Q->rear = node;
	}else{
		Q->rear->next = node;
		Q->rear = node;
	}
	Q->count++;
	return TRUE;
}

/**
 *  @name        : Status DeQueue(Queue *Q)
 *	@description : 出队操作
 *	@param		 : 队列指针Q
 *	@return		 : 成功-TRUE; 失败-FLASE
 *  @notice      : None
 */
Status DeQueue(Queue *Q){
	Node *p = NULL;
	if (Q->front != NULL){
		if (Q->front == Q->rear){
			free(Q->front);
			Q->count = 0;
			Q->front = Q->rear = NULL;
		} else {
			p = Q->front;
			Q->count--;
			Q->front = p->next;
			free(p);
		}
	}
	return TRUE;
}

/**
 *  @name        : void ClearQueue(Queue *Q)
 *	@description : 清空队列
 *	@param		 : 队列指针Q
 *	@return		 : None
 *  @notice      : None
 */
void ClearQueue(Queue *Q){
	Node *p = Q->front;
	Node *p1 = NULL;
	while (p != NULL){
		p1 = p->next;
		free(p);
		p = p1;
	}
	Q->front = Q->rear = NULL;
	Q->count = 0;
	return ;
}

/**
 *  @name        : Status TraverseQueue(const Queue *Q, void (*foo)(Node *q))
 *	@description : 遍历函数操作
 *	@param		 : 队列指针Q，操作函数指针foo
 *	@return		 : None
 *  @notice      : None
 */

Status TraverseQueue(const Queue *Q, void (*foo)(Node *q)){
	Node *p = Q->front;
	while(p != NULL) {
		foo(p);
		p = p->next;
	}
	return TRUE;
}

/**
 *  @name        : void select()
 *	@description : 菜单选择
 *	@param		 : None
 *	@return		 : None
 *  @notice      : None
 */

void select() {
    int num;
    while (1) {
        printf("****************泛型队列******************\n");
        printf("*            1----队列判空               *\n");
        printf("*            2----获取队列长度           *\n");
        printf("*            3----出队                   *\n");
        printf("*            4----清空队列               *\n");
        printf("*            5----销毁队列               *\n");
        printf("*            6----输出队列               *\n");
        printf("*            7----查看对头元素           *\n");
        printf("******************************************\n");
        printf("请输入你想进行的操作：");
        scanf("%d", &num);
        getchar();
        fflush(stdin);
        if (num == 4) {
            ClearQueue(&Q);
            printf("队列已清空！\n");
            system("pause");
            system("cls");
            break;
        }
        switch (num) {
            case 1: {
                if (IsEmptyQueue(&Q)) {
                    printf("队列为空！\n");
                } else {
                    printf("队列不为空！\n");
                }
                break;
            } case 2: {
                printf("队列长度为：%d\n", LengthQueue(&Q));
                break;
            } case 3: {
                if (DeQueue(&Q)) {
                    printf("出队成功！\n");
                } else {
                    printf("出队失败！\n");
                }
                break;
            } case 5: {
                printf("是否销毁队列，清空后将退出！(Y/N)：");
                if (toupper(getchar()) == 'Y') {
                    DestoryQueue(&Q);
                    printf("队列已清空，请退出！\n");
                    getch();
                    exit(EXIT_SUCCESS);
                } else {
                    break;
                }
            } case 6: {
                if (IsEmptyQueue(&Q)) {
                    printf("队列为空！\n");
                    break;
                } else {
                    printf("对头--");
                    if (flag == INT) {
                        TraverseQueue(&Q, out_int);
                    } else if (flag == FLOAT) {
                        TraverseQueue(&Q, out_float);
                    } else if (flag == CHAR) {
                        TraverseQueue(&Q, out_char);
                    }
                    printf("队尾");
                    printf("\n");
                    break;
                }
            } case 7: {
                if (IsEmptyQueue(&Q)) {
                    printf("队列为空！\n");
                    break;
                } else {
                    if (flag == INT) {
                        int *n = NULL;
                        n = (int *)malloc(sizeof(int));
                        GetHeadQueue(&Q, n);
                        printf("对头元素为：%d\n", *n);
                        free(n);
                    } else if (flag == FLOAT) {
                        float *f = NULL;
                        f = (float *)malloc(sizeof(float));
                        GetHeadQueue(&Q, f);
                        printf("对头元素：%f\n", *f);
                        free(f);
                    } else if (flag == CHAR) {
                        char *c = NULL;
                        c = (char *)malloc(sizeof(char));
                        GetHeadQueue(&Q, c);
                        printf("对头元素：%c\n", *c);
                        free(c);
                    }
                    break;
                }
            } default: printf("输入不正确，请重新输入！\n");
                        break;
        }
        system("pause");
        system("cls");
    }
}

/**
 *  @name        : void out_int(Node *q)
 *	@description : 输出整型数据
 *	@param		 : 结构体指针q
 *	@return		 : None
 *  @notice      : None
 */

void out_int(Node *q) {
    int *p = (int *)q->data;
	printf(" %d --", *p);
}

 /**
 *  @name        : void out_float(Node *q)
 *	@description : 输出浮点型数据
 *	@param		 : 结构体指针q
 *	@return		 : None
 *  @notice      : None
 */

void out_float(Node *q) {
    float *p = (float *)q->data;
	printf(" %f --", *p);
}

/**
 *  @name        : void out_char(Node *q)
 *	@description : 输出字符型数据
 *	@param		 : 结构体指针q
 *	@return		 : None
 *  @notice      : None
 */

void out_char(Node *q) {
    char *p = (char *)q->data;
    printf(" %c --", *p);
}
