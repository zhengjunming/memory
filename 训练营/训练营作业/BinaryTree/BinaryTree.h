
/**************************************************************
*	Multi-Include-Prevent Section
**************************************************************/
#ifndef BINARYTREE_H_INCLUDED
#define BINARYTREE_H_INCLUDED

/**************************************************************
*	Include File Section
**************************************************************/
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include <conio.h>

/**************************************************************
*	Macro Define Section
**************************************************************/
#define OK 1
#define ERROR 0
#define OVERFLOW -1
#define FALSE 0
#define TRUE 1

/**************************************************************
*	Struct Define Section
**************************************************************/
typedef char TElemType;
typedef int Status;
typedef struct BiTNode {
    TElemType data; // 数据域
    struct BiTNode *lchild, *rchild; // 左、右孩子指针
} BiTNode, *BiTree; // 二叉链表

/**************************************************************
*	Prototype Declare Section
**************************************************************/

/**
 *  @name        : Status InitBiTree(BiTree T)
 *	@description : 初始化一个二叉树
 *	@param		 : 根结点T
 *	@return		 : 成功-OK; 失败-ERROR
 *  @notice      : None
 */
Status InitBiTree(BiTree T);

/**
 *  @name        : void DestroyBiTree(BiTree T)
 *	@description : 销毁一个二叉树
 *	@param		 : 根结点T
 *	@return		 : None
 *  @notice      : None
 */
void DestroyBiTree(BiTree T);

/**
 *  @name        : Status CreateBiTree(BiTree T, TElemType **definition)
 *	@description : 根据一个字符串生成一个二叉树
 *	@param		 : 根结点T, 字符串definition
 *	@return		 : OK
 *  @notice      : None
 */
Status CreateBiTree(BiTree T, TElemType **definition);

/**
 *  @name        : int BiTreeDepth(BiTree T)
 *	@description : 求二叉树的深度
 *	@param		 : 根结点T
 *	@return		 : 深度depth
 *  @notice      : None
 */
int BiTreeDepth(BiTree T);

/**
 *  @name        : void PreOrderTraverse(const BiTree T, void(*visit)(TElemType))
 *	@description : 前序遍历二叉树
 *	@param		 : 根结点T，操作函数指针visit
 *	@return		 : None
 *  @notice      : None
 */
void PreOrderTraverse(const BiTree T, void(*visit)(TElemType));

/**
 *  @name        : void InOrderTraverse(const BiTree T, void(*visit)(TElemType))
 *	@description : 中序遍历二叉树
 *	@param		 : 根结点T，操作函数指针visit
 *	@return		 : None
 *  @notice      : None
 */
void InOrderTraverse(const BiTree T, void(*visit)(TElemType));

/**
 *  @name        : void PostOrderTraverse(const BiTree T, void(*visit)(TElemType))
 *	@description : 后序遍历二叉树
 *	@param		 : 根结点T，操作函数指针visit
 *	@return		 : None
 *  @notice      : None
 */
void PostOrderTraverse(const BiTree T, void(*visit)(TElemType));

/**
 *  @name        : void PrintNodeByLevel(BiTree T)
 *	@description : 层次遍历二叉树
 *	@param		 : 根结点T
 *	@return		 : None
 *  @notice      : None
 */
void PrintNodeByLevel(BiTree T);

/**
 *  @name        : void LevelOrderTraverse(const BiTree T, void(*visit)(TElemType), int level)
 *	@description : 输入第level层的所有节点元素
 *	@param		 : 根结点T，操作函数指针visit,层次数level
 *	@return		 : 成功返回OK， 失败返回ERROR
 *  @notice      : None
 */
Status LevelOrderTraverse(const BiTree T, void(*visit)(TElemType), int level);

/**
 *  @name        : void visit(TElemType ch)
 *	@description : 输出二叉树元素
 *	@param		 : 元素ch
 *	@return		 : None
 *  @notice      : None
 */
void visit(TElemType);

/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/
#endif // BINARYTREE_H_INCLUDED
