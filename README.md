# MissedBundle

## How to Contribute

1. clone or fork the repo
2. make your own branch ([see branch naming style](#branch-naming-style))
3. setup a development environment
4. make changes
5. commit your changes ([see commit message naming style](#commit-message-naming-style))
6. make a pull request
7. discuss on your changes
8. merge ^_^

> All of the listed naming rules should be followed. But don't give it too much matter. These rules exist only for keeping the order of the repo and work done on mode development

## Branch Naming Style

The repository has adopted the following style of branch naming:  
  `<branch_type>: <functionality_description>`

---

### Branch names examples

- `feature/opened_chest_ui`
- `fix/crashing_on_lounch`

### Branch types

- `feature`: new featuers should be implemented hear
- `fix`: any kindes of fixes should be done hear

## Commit Message Naming Style

The repository has adopted the following style of commits:  
  `<commit_type>: <commit_message>`

---

### Usage example

```sh
feat: add super duper feature
^--^  ^------------^
|     |
|     +-> Summary in present tense.
|
+-------> Commit type.
```

### Commit types

- `feat`: A new feature
- `fix`: A fix of a piece of functionality
- `docs`: Documentation only changes
- `perf`: A code change which improves performance
- `refactor`: A code change that neither fixes anything
- `revert`: A revert to previous commit