
**Attention:**<br>
The program should be run on Nexus 5X API 25(Android 7.1.1). This is because the code utilizes the screen length and width of the Nexus 5X as the map array for the game, and therefore if the user uses a different phone with a different screen size than the Nexus 5X, then the program will attempt to go out of bounds of an array for various objects in the game. <p>
Also, the pogramme don't support multi-touch. The user cannot simultaneously touch multi buttons. And the directional buttons must be pressed repeatedly instead of holding it like in tradtional platformers.

**Maneuvering the program:**
1) When opening the project, there is a possibility that the following error <br />  "\pathtousername\username\additionalpathstoproject\LeYuArronAssignment3-master does not exist" and the window that shows the error will prompt you to either get rid of them or keep them. You should get rid of them because ther error only appears when first downloading the project and does not appear again after getting rid of them. This error is because one team member merge two different name packages in the github, so the name is not unified. **It won't affect the program at all, just ignore it.** If you get the error that the git root is being called but does not exist, that just means that the user is not directly linked to the github respository or has configured any github related version controls to either the incorrect repository or has not configured it at all.  <br />
2) The first time you open the project, **we recommend you clean the project using Build-Clean Project.** in  Android Studio. Because there are a lot trash files in the projects when we compile the programme, maybe the program can't be run properly if you don't clean them in your computer.

User controls:<p>
1.the user will be using 4 buttons designated on the screen to control the super mario. The square button is a jump button to make mario jump. <p>
2.The triangle button is an attack button. After th esuper mario transform to "fire mario", it can emit fireballs using that attack button. The direction of fireball depends on the direction that mario stand towards when he emit the fireball. After mario move left, it means mario stands towards left, after mario move right, it means mario stands towards right. <p>
3.The left arrow and right arraw are the buttons control mario's movement. For example, if the user click left button once, the mario will move left one grid. <p>
4.Mario has 3 health intotal. The mario will respawn to an area after they die. If mario runs out of health, te program will be terminated.<p>
<p>
Items:<p>
Coins: This item appears throughout the level and will only increase the player's score upon collection. The user will gain 200 rewards for one coin. <p>
Mushroom: After the small mario hit the itemblock, the mushroom will appear. The mushroom won't move as the real super mario bro game, it just be there like the fireflower. Mario should jump and eat it. After eatting it, the small mario will become Super mario.The user will gain 1000 rewards after eatting it.<p>
Fireflower:After the super mario hit the itemblock, the fireflower will appear. After eatting it, the super mario will become fire mario. The user will gain 1000 rewards after eating it. Fire mario can emit fireballs.<p>
<p>
Objects:<p>
Item boxes:This object provides the player with either a mushroom or fireflower upon hitting. Then it becomes a dirt.<p>
Bricks: This object is nothing more than an obstacle or platform for Mario to stand on or hit. Super mario or fire mario can break the brick, user can gain 50 rewards if he breake one brick. <p>
Pipes: This object accompanie the Piranha plant, fireballs and mario can't walk through pipes.<p>
<p>
Enemies:<p>
Buzzy Beetle: It is small turtle that hides in its shell when jumped on, and it is immune to fireballs.<p>
Koopa Paratroopa: This enemy flies up and down. This enemy is susceptible to fireballs. The player must jump on the paratroopa once to change its behavior to Koopa Troopa and then proceed to defeat this enemy in the same way as Koopa Troopa.<p>
Piranha plant:This enemy is horizontally stationary. Mario can only defeat this enemy with fireballs, otherwise the player should just avoid this enemy. It pops up from pipes and can only defeat Mario while exposed. It will not rise up if mario if on the top of the pipe.<p>
<p>
