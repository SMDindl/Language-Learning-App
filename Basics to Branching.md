Creating and merging branches

1. Open Terminal

2. Check your branch

Type: 
git branch.

If not on main, type: 
git checkout main.


3. Create and switch to a new branch

Create and switch to branch
Type: 
git checkout -b branch-[yourname].

Or just switch to branch if already created
Type:
git checkout branch-[yourname]

4. Add/edit a file

Manually add a new file or edit an existing one.


5. Stage and commit changes

Type:
git add . 

to stage your file(s).


Type: 
git commit -m "Added/edited file" 

to commit your changes.


6. Push branch to GitHub

Type: 
git push origin branch-[yourname] 

to push the branch.


7. Merge branch into main

Switch back to main, type: 
git checkout main


Type:
git merge branch-[yourname] 

to merge.


8. Push main to GitHub

Type: 
git push origin main 

to push the updated main branch.


9. Pull latest changes

Type: 
git pull origin main 

to pull any new changes from GitHub.

