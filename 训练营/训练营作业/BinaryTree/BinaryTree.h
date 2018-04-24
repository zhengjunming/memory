
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
    TElemType data; // ������
    struct BiTNode *lchild, *rchild; // ���Һ���ָ��
} BiTNode, *BiTree; // ��������

/**************************************************************
*	Prototype Declare Section
**************************************************************/

/**
 *  @name        : Status InitBiTree(BiTree T)
 *	@description : ��ʼ��һ��������
 *	@param		 : �����T
 *	@return		 : �ɹ�-OK; ʧ��-ERROR
 *  @notice      : None
 */
Status InitBiTree(BiTree T);

/**
 *  @name        : void DestroyBiTree(BiTree T)
 *	@description : ����һ��������
 *	@param		 : �����T
 *	@return		 : None
 *  @notice      : None
 */
void DestroyBiTree(BiTree T);

/**
 *  @name        : Status CreateBiTree(BiTree T, TElemType **definition)
 *	@description : ����һ���ַ�������һ��������
 *	@param		 : �����T, �ַ���definition
 *	@return		 : OK
 *  @notice      : None
 */
Status CreateBiTree(BiTree T, TElemType **definition);

/**
 *  @name        : int BiTreeDepth(BiTree T)
 *	@description : ������������
 *	@param		 : �����T
 *	@return		 : ���depth
 *  @notice      : None
 */
int BiTreeDepth(BiTree T);

/**
 *  @name        : void PreOrderTraverse(const BiTree T, void(*visit)(TElemType))
 *	@description : ǰ�����������
 *	@param		 : �����T����������ָ��visit
 *	@return		 : None
 *  @notice      : None
 */
void PreOrderTraverse(const BiTree T, void(*visit)(TElemType));

/**
 *  @name        : void InOrderTraverse(const BiTree T, void(*visit)(TElemType))
 *	@description : �������������
 *	@param		 : �����T����������ָ��visit
 *	@return		 : None
 *  @notice      : None
 */
void InOrderTraverse(const BiTree T, void(*visit)(TElemType));

/**
 *  @name        : void PostOrderTraverse(const BiTree T, void(*visit)(TElemType))
 *	@description : �������������
 *	@param		 : �����T����������ָ��visit
 *	@return		 : None
 *  @notice      : None
 */
void PostOrderTraverse(const BiTree T, void(*visit)(TElemType));

/**
 *  @name        : void PrintNodeByLevel(BiTree T)
 *	@description : ��α���������
 *	@param		 : �����T
 *	@return		 : None
 *  @notice      : None
 */
void PrintNodeByLevel(BiTree T);

/**
 *  @name        : void LevelOrderTraverse(const BiTree T, void(*visit)(TElemType), int level)
 *	@description : �����level������нڵ�Ԫ��
 *	@param		 : �����T����������ָ��visit,�����level
 *	@return		 : �ɹ�����OK�� ʧ�ܷ���ERROR
 *  @notice      : None
 */
Status LevelOrderTraverse(const BiTree T, void(*visit)(TElemType), int level);

/**
 *  @name        : void visit(TElemType ch)
 *	@description : ���������Ԫ��
 *	@param		 : Ԫ��ch
 *	@return		 : None
 *  @notice      : None
 */
void visit(TElemType);

/**************************************************************
*	End-Multi-Include-Prevent Section
**************************************************************/
#endif // BINARYTREE_H_INCLUDED
