If you already installed Java.
Then Right Click on RunWithPowershell.ps1 file

Then select "Run With PowerSell" option.

Then input "Y";

"https://beta.smsroute.to/sendsms?username=username&password=password&type=type&source=sender_name&destinations=14013162703,14013162703&message=message"

The applcation will ask for Password Username And Y for confirmation

The numbers shoul be on Number.txt file. One Number per line.

Then the app will send The 2 GET request  every 600ms for each number in Number.txt

If you want to change or add Randoms 
Goto randomSringRun.java 
Find TODO: Comments that explains the process/