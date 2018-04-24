#include "queue.h"

extern int flag;
extern Queue Q;

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
void InitQueue(Queue *Q){
	Q->front = NULL;
	Q->rear = NULL;
	Q->count = 0;
	Q->data_size = 0;
}

/**
 *  @name        : void DestoryQueue(Queue *Q)
 *	@description : ���ٶ���
 *	@param		 : ����ָ��Q
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
 *	@description : �������Ƿ�Ϊ��
 *	@param		 : ����ָ��Q
 *	@return		 : ��-TRUE; δ��-FLASE
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
 *	@description : �鿴��ͷԪ��
 *	@param		 : ����ָ��Q�����Ԫ��e
 *	@return		 : �ɹ�-TRUE; ʧ��-FLASE
 *  @notice      : �����Ƿ��
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
 *	@description : ȷ�����г���
 *	@param		 : ����ָ��Q
 *	@return		 : ���г���count
 *  @notice      : None
 */
int LengthQueue(Queue *Q){
	return Q->count;
}

/**
 *  @name        : Status EnQueue(Queue *Q, void *data)
 *	@description : ��Ӳ���
 *	@param		 : ����ָ��Q,�������ָ��data
 *	@return		 : �ɹ�-TRUE; ʧ��-FLASE
 *  @notice      : �����Ƿ����˻�Ϊ��
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
 *	@description : ���Ӳ���
 *	@param		 : ����ָ��Q
 *	@return		 : �ɹ�-TRUE; ʧ��-FLASE
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
 *	@description : ��ն���
 *	@param		 : ����ָ��Q
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
 *	@description : ������������
 *	@param		 : ����ָ��Q����������ָ��foo
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
 *	@description : �˵�ѡ��
 *	@param		 : None
 *	@return		 : None
 *  @notice      : None
 */

void select() {
    int num;
    while (1) {
        printf("****************���Ͷ���******************\n");
        printf("*            1----�����п�               *\n");
        printf("*            2----��ȡ���г���           *\n");
        printf("*            3----����                   *\n");
        printf("*            4----��ն���               *\n");
        printf("*            5----���ٶ���               *\n");
        printf("*            6----�������               *\n");
        printf("*            7----�鿴��ͷԪ��           *\n");
        printf("******************************************\n");
        printf("������������еĲ�����");
        scanf("%d", &num);
        getchar();
        fflush(stdin);
        if (num == 4) {
            ClearQueue(&Q);
            printf("��������գ�\n");
            system("pause");
            system("cls");
            break;
        }
        switch (num) {
            case 1: {
                if (IsEmptyQueue(&Q)) {
                    printf("����Ϊ�գ�\n");
                } else {
                    printf("���в�Ϊ�գ�\n");
                }
                break;
            } case 2: {
                printf("���г���Ϊ��%d\n", LengthQueue(&Q));
                break;
            } case 3: {
                if (DeQueue(&Q)) {
                    printf("���ӳɹ���\n");
                } else {
                    printf("����ʧ�ܣ�\n");
                }
                break;
            } case 5: {
                printf("�Ƿ����ٶ��У���պ��˳���(Y/N)��");
                if (toupper(getchar()) == 'Y') {
                    DestoryQueue(&Q);
                    printf("��������գ����˳���\n");
                    getch();
                    exit(EXIT_SUCCESS);
                } else {
                    break;
                }
            } case 6: {
                if (IsEmptyQueue(&Q)) {
                    printf("����Ϊ�գ�\n");
                    break;
                } else {
                    printf("��ͷ--");
                    if (flag == INT) {
                        TraverseQueue(&Q, out_int);
                    } else if (flag == FLOAT) {
                        TraverseQueue(&Q, out_float);
                    } else if (flag == CHAR) {
                        TraverseQueue(&Q, out_char);
                    }
                    printf("��β");
                    printf("\n");
                    break;
                }
            } case 7: {
                if (IsEmptyQueue(&Q)) {
                    printf("����Ϊ�գ�\n");
                    break;
                } else {
                    if (flag == INT) {
                        int *n = NULL;
                        n = (int *)malloc(sizeof(int));
                        GetHeadQueue(&Q, n);
                        printf("��ͷԪ��Ϊ��%d\n", *n);
                        free(n);
                    } else if (flag == FLOAT) {
                        float *f = NULL;
                        f = (float *)malloc(sizeof(float));
                        GetHeadQueue(&Q, f);
                        printf("��ͷԪ�أ�%f\n", *f);
                        free(f);
                    } else if (flag == CHAR) {
                        char *c = NULL;
                        c = (char *)malloc(sizeof(char));
                        GetHeadQueue(&Q, c);
                        printf("��ͷԪ�أ�%c\n", *c);
                        free(c);
                    }
                    break;
                }
            } default: printf("���벻��ȷ�����������룡\n");
                        break;
        }
        system("pause");
        system("cls");
    }
}

/**
 *  @name        : void out_int(Node *q)
 *	@description : �����������
 *	@param		 : �ṹ��ָ��q
 *	@return		 : None
 *  @notice      : None
 */

void out_int(Node *q) {
    int *p = (int *)q->data;
	printf(" %d --", *p);
}

 /**
 *  @name        : void out_float(Node *q)
 *	@description : �������������
 *	@param		 : �ṹ��ָ��q
 *	@return		 : None
 *  @notice      : None
 */

void out_float(Node *q) {
    float *p = (float *)q->data;
	printf(" %f --", *p);
}

/**
 *  @name        : void out_char(Node *q)
 *	@description : ����ַ�������
 *	@param		 : �ṹ��ָ��q
 *	@return		 : None
 *  @notice      : None
 */

void out_char(Node *q) {
    char *p = (char *)q->data;
    printf(" %c --", *p);
}
