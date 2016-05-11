package messagingAplicationPackage.userInterface;

import messagingAplicationPackage.classes.Message;

public class CmdInterface {

    private String out;

    public CmdInterface() {
        out = null;
    }

    public String printInstructions() {
        return out = "You can type end at any time to exit!\n Please Enter your UserName: ";
    }

    public String printOptions() {

        return out = "Please type the number of option you want to pick! \n 1 > Send Message \n 2 > View Messages \n 3 > LogOut";
    }

    public String printInvalidUsername() {
        return out = "Invalid UserName! Try Again: ";
    }

    public String printExit() {
        return out = "The System Going to Exit Now";
    }

    public String printWelcomeUser(String username) {
        return out = "Welcome " + username;
    }

    public String printRecipientNames() {
        return out = "Please Type Recipient Names: e.g. Rabih, Paul";
    }

    public String printEnterMessage() {
        return out = "Please Type Your Message: ";
    }

    public String printMessageSent() {
        return out = "Your Message is sent :-) ";
    }

    public String printNoMessages() {
        return out = "You have no messages!!!";
    }

    public String printViewMessage(Message messageRecived) {
        return out = "Your Messages: \n Sender: " + messageRecived.getFrom() + "\n Message: " + messageRecived.getMessage();
    }

    public String printLogOutMessage() {
        return out = "Thank You, You are Logged out!";
    }

    public String printInvalidChoice() {
        return out = "Invalid Choice! Please pick 1 or 2 or 3";
    }
}
