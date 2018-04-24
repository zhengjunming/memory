#include "BinaryTree.h"

extern BiTree T;

/**
 *  @name        : Status InitBiTree(BiTree T)
 *	@description : ��ʼ��һ��������
 *	@param		 : �����T
 *	@return		 : �ɹ�-OK; ʧ��-ERROR
 *  @notice      : None
 */
Status InitBiTree(BiTree T) {
    if (T == NULL) {
        return ERROR;
    }
    T->lchild = NULL;
    T->rchild = NULL;
    return OK;
}

/**
 *  @name        : void DestroyBiTree(BiTree T)
 *	@description : ����һ��������
 *	@param		 : �����T
 *	@return		 : None
 *  @notice      : None
 */
void DestroyBiTree(BiTree T) {
    if (NULL != T) {
        if (T->lchild) {
            DestroyBiTree(T->lchild);
        }
        if (T->rchild) {
            DestroyBiTree(T->rchild);
        }
        free(T);
        T = NULL;
    }
}

/**
 *  @name        : Status CreateBiTree(BiTree T, TElemType **definition)
 *	@description : ����һ���ַ�������һ��������
 *	@param		 : �����T, �ַ���definition
 *	@return		 : OK
 *  @notice      : None
 */
Status CreateBiTree(BiTree T, TElemType **definition) {
    while (**definition != '\0') {
        if (**definition == '+' || **definition == '*' || **definition == '-' || **definition == '/') {
            T->data = **definition;
            ++(*definition);
            T->lchild = (BiTNode *)malloc(sizeof(BiTNode));
            T->rchild = (BiTNode *)malloc(sizeof(BiTNode));
            CreateBiTree(T->lchild, definition);
            CreateBiTree(T->rchild, definition);
            return OK;
        } else if (**definition >= '0' && **definition <= '9') {
            T->data = **definition;
            T->lchild = NULL;
            T->rchild = NULL;
            ++(*definition);
            return OK;
        } else {
            ++(*definition);
        }
    }
    return OK;
}

/**
 *  @name        : int BiTreeDepth(BiTree T)
 *	@description : ������������
 *	@param		 : �����T
 *	@return		 : ���depth
 *  @notice      : None
 */
int BiTreeDepth(BiTree T) {
    int depth = 0;
    if (NULL != T) {
        int leftDepth = BiTreeDepth(T->lchild);
        int rightDepth = BiTreeDepth(T->rchild);
        depth = leftDepth >= rightDepth ? leftDepth + 1 : rightDepth + 1;
    }
    return depth;
}

/**
 *  @name        : void PreOrderTraverse(const BiTree T, void(*visit)(TElemType))
 *	@description : ǰ�����������
 *	@param		 : �����T����������ָ��visit
 *	@return		 : None
 *  @notice      : None
 */
void PreOrderTraverse(const BiTree T, void(*visit)(TElemType)) {
    if (NULL != T) {
        visit(T->data);
        PreOrderTraverse(T->lchild, visit);
        PreOrderTraverse(T->rchild, visit);
    }
}

/**
 *  @name        : void InOrderTraverse(const BiTree T, void(*visit)(TElemType))
 *	@description : �������������
 *	@param		 : �����T����������ָ��visit
 *	@return		 : None
 *  @notice      : None
 */
void InOrderTraverse(const BiTree T, void(*visit)(TElemType)) {
    if (NULL != T) {
        InOrderTraverse(T->lchild, visit);
        visit(T->data);
        InOrderTraverse(T->rchild, visit);
    }
}

/**
 *  @name        : void PostOrderTraverse(const BiTree T, void(*visit)(TElemType))
 *	@description : �������������
 *	@param		 : �����T����������ָ��visit
 *	@return		 : None
 *  @notice      : None
 */
void PostOrderTraverse(const BiTree T, void(*visit)(TElemType)) {
    if (NULL != T) {
        PostOrderTraverse(T->lchild, visit);
        PostOrderTraverse(T->rchild, visit);
        visit(T->data);
    }
}

/**
 *  @name        : void LevelOrderTraverse(const BiTree T, void(*visit)(TElemType), int level)
 *	@description : �����level������нڵ�Ԫ��
 *	@param		 : �����T����������ָ��visit,�����level
 *	@return		 : �ɹ�����OK�� ʧ�ܷ���ERROR
 *  @notice      : None
 */
Status LevelOrderTraverse(BiTree T, void(*visit)(TElemType), int level) {
    if (!T || level < 0) {
        return ERROR;
    } if (level == 0) {
        visit(T->data);
        return OK;
    }
    return LevelOrderTraverse(T->lchild, visit, level - 1) +
            LevelOrderTraverse(T->rchild, visit, level - 1);
}

/**
 *  @name        : void PrintNodeByLevel(BiTree T)
 *	@description : ��α���������
 *	@param		 : �����T
 *	@return		 : None
 *  @notice      : None
 */
void PrintNodeByLevel(BiTree T) {
    int level;
    for (level = 0; ; level++)
        if (!LevelOrderTraverse(T, visit, level))
            break;
}

/**
 *  @name        : void visit(TElemType ch)
 *	@description : ���������Ԫ��
 *	@param		 : Ԫ��ch
 *	@return		 : None
 *  @notice      : None
 */
void visit(TElemType ch) {
    printf("%c", ch);
}
