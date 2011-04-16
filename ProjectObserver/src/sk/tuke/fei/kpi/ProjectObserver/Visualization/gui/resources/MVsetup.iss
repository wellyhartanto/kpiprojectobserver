; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppVersion=1.0
AppCopyright=KPI FEI TUKE
AppName=Project Observer

AppPublisher=KPI FEI TUKE
AppPublisherURL=http://kpi.fei.tuke.sk/
DefaultDirName={pf}\ProjectObserver
DefaultGroupName=ProjectObserver
OutputDir=C:\Temp\POsetup
OutputBaseFilename=ProjectObserverSetup
Compression=lzma
SolidCompression=yes
SetupIconFile=C:\EclipseWorkspace\ProjectObserver\src\sk\tuke\fei\kpi\ProjectObserver\Visualization\gui\resources\images\icondesktop.ico
WindowVisible=no



[Languages]
Name: "german"; MessagesFile: "compiler:Languages\Slovak.isl"
Name: "english"; MessagesFile: "compiler:Default.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}";

[Files]
Source: "C:\Temp\POsource\start.bat"; DestDir: "{app}"; Flags: ignoreversion
Source: "C:\Temp\POsource\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{group}\Project Observer"; Filename: "{app}\start.bat";  WorkingDir: "{app}"; IconFileName: "{app}\icondesktop.ico" ;Flags: runminimized
Name: "{group}\{cm:UninstallProgram,Project Observer}"; Filename: "{uninstallexe}"
Name: "{commondesktop}\Project Observer"; Filename: "{app}\start.bat"; Tasks: desktopicon; WorkingDir: "{app}"; IconFileName: "{app}\icondesktop.ico" ;Flags: runminimized


[Run]
Filename: "{app}\start.bat"; Description: "{cm:LaunchProgram,Project Observer}"; Flags: shellexec postinstall skipifsilent


