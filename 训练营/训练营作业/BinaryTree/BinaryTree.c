#include "BinaryTree.h"

extern BiTree T;

/**
 *  @name        : Status InitBiTree(BiTree T)
 *	@description : 初始化一个二叉树
 *	@param		 : 根结点T
 *	@return		 : 成功-OK; 失败-ERROR
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
 *	@description : 销毁一个二叉树
 *	@param		 : 根结点T
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
 *	@description : 根据一个字符串生成一个二叉树
 *	@param		 : 根结点T, 字符串definition
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
 *	@description : 求二叉树的深度
 *	@param		 : 根结点T
 *	@return		 : 深度depth
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
 *	@description : 前序遍历二叉树
 *	@param		 : 根结点T，操作函数指针visit
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
 *	@description : 中序遍历二叉树
 *	@param		 : 根结点T，操作函数指针visit
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
 *	@description : 后序遍历二叉树
 *	@param		 : 根结点T，操作函数指针visit
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
 *	@description : 输入第level层的所有节点元素
 *	@param		 : 根结点T，操作函数指针visit,层次数level
 *	@return		 : 成功返回OK， 失败返回ERROR
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
 *	@description : 层次遍历二叉树
 *	@param		 : 根结点T
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
 *	@description : 输出二叉树元素
 *	@param		 : 元素ch
 *	@return		 : None
 *  @notice      : None
 */
void visit(TElemType ch) {
    printf("%c", ch);
}
