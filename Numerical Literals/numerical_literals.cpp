// pa_test.txt
#include <iostream>
#include <fstream>
#include <cctype>
#include <string>
#include <typeinfo>

using namespace std;

// Function Prototypes
string cleanLiteral(string);
bool isValidLiteral(string);
bool isSpecialCharacter(char);
bool indexHasExponent(string, int);
bool indexHasPlusOrMinus(string, int);
bool isInvalidCharacterAfterPlusOrMinus(string, int);
bool isInvalidFirstCharacter(string);
bool hasInvalidCharactersAroundExponent(string, int);
bool isInvalidSingleLiteral(string);
bool indexHasPeriod(string, int);
bool isAllowedFirstCharacter(char);

// Constant Definitions
const int MAX_STRING_LENGTH = 50;
const int NUMBER_OF_SPECIAL_CHARACTERS = 5;
const int NUMBER_ALLOWED_FIRST_CHARACTERS = 3;
const int INDEX_0 = 0;
const int FILE_EXTENSION_LENGTH = 4;
const char LOWERCASE_EXPONENT = 'e';
const char UPPERCASE_EXPONENT = 'E';
const char PERIOD = '.';
const char PLUS_SIGN = '+';
const char MINUS_SIGN = '-';
const string DIRTY_LITERAL = "Dirty Literal";
const string INVALID_LITERAL = "is NOT a valid numeric Literal";
const string VALID_LITERAL = "is a valid numeric literal";
const string OUTPUTFILE_EXTENSION = "_output.txt";
const char SPECIAL_CHARACTERS[] = {LOWERCASE_EXPONENT, UPPERCASE_EXPONENT, PERIOD, PLUS_SIGN, MINUS_SIGN};
const char ALLOWED_FIRST_CHARACTERS[] = {PERIOD, PLUS_SIGN, MINUS_SIGN};

int main() {
    string rawLine = "";
    string processedLine = "";
    string processedLineStatus = "";
    bool isValidNumericLiteral = false;

    fstream inputFile;
    ofstream outputFile;
    string inputFileName = "";
    string outputFileName = "";
    
    cout << "Enter the name of your file: ";
    getline(cin, inputFileName);
    
    inputFile.open(inputFileName);
    
    if (inputFile.is_open()) {
        
        outputFileName = inputFileName.substr(INDEX_0, inputFileName.length() - 
            FILE_EXTENSION_LENGTH) + OUTPUTFILE_EXTENSION;
        outputFile.open(outputFileName);
        
        while (getline(inputFile, rawLine)) {
            processedLine = cleanLiteral(rawLine);
            if (processedLine != DIRTY_LITERAL) {
                isValidNumericLiteral = isValidLiteral(processedLine);
                isValidNumericLiteral ? 
                    processedLineStatus = VALID_LITERAL : 
                        processedLineStatus = INVALID_LITERAL;
            } else {
                processedLineStatus = INVALID_LITERAL;
            }
            cout << rawLine << "\t" << processedLineStatus << endl;
            outputFile << rawLine << "\t" << processedLineStatus << endl;
        }
    }

    return 0;
}

/* 	Method: cleanLiteral
	Description: Completes initial validation of literal.  
*/
string cleanLiteral(string rawLine) {
    bool isDigitOrSpecialCharacter = true;

    if (rawLine.length() <= INDEX_0 || rawLine.length() > MAX_STRING_LENGTH) {
        return DIRTY_LITERAL;
    }
        
    //cout << "rawLine = " << rawLine << endl;
    int i = 0;
    while (i < rawLine.length() && isDigitOrSpecialCharacter) {
        //cout << "rawLine[i] = " << rawLine[i] << " ";
        isDigitOrSpecialCharacter = isdigit(rawLine[i]) || isSpecialCharacter(rawLine[i]);
        //cout << "isDigitOrSpecialCharacter = " << isDigitOrSpecialCharacter << endl;
        i++;
    }

    return isDigitOrSpecialCharacter ? rawLine : DIRTY_LITERAL;
} 

/* 	Method: isValidLiteral 
	Description: Further validates given literal according to specifications.  
*/
bool isValidLiteral(string literal) {
    int i = 0;
    bool isValidLiteral = true;

    if (isInvalidSingleLiteral(literal) || isInvalidFirstCharacter(literal)) 
        isValidLiteral = false;

    while (i < literal.length() && isValidLiteral) {
        if (literal[i] == ' ') isValidLiteral = false;
        if (indexHasExponent(literal, i)) {
            if (i != literal.length() - 1) {
                if (hasInvalidCharactersAroundExponent(literal, i)) {
                    isValidLiteral = false;
                }
            } else {
                isValidLiteral = false;
            }   
           
        } else if (indexHasPlusOrMinus(literal, i)) {
            if (i != literal.length() - 1) {
                if (isInvalidCharacterAfterPlusOrMinus(literal, i + 1)) {
                    isValidLiteral = false;
                }
            } else {
                isValidLiteral = false;
            }
        } else if (indexHasPeriod(literal, i)) {
            if (i != literal.length() - 1) {
                if(isSpecialCharacter(literal[i + 1])) 
                    isValidLiteral = false;
             }
        }  
        i++;               
    }
    return isValidLiteral; 
}

/* 	Method: indexHasPeriod
	Description: True if the given index of a literal has a period.  
*/
bool indexHasPeriod(string literal, int index) {
    return literal[index] == PERIOD;
}

/* 	Method: isInvalidSingleLiteral 
	Description: True if single length literal is invalid.  
*/
bool isInvalidSingleLiteral(string literal) {
    bool isInvalidSingleLiteral = false;

    if (literal.length() != 1) 
        isInvalidSingleLiteral = false;
    else {
        isInvalidSingleLiteral = !isdigit(literal[0]);
    }

    return isInvalidSingleLiteral;
}

/* 	Method: indexHasPlusOrMinus
	Description: True if given index of a literal has a plus a or minus sign.  
*/
bool indexHasPlusOrMinus(string literal, int index) {
    return (literal[index] == PLUS_SIGN || literal[index] == MINUS_SIGN);
}

/* 	Method: isInvalidCharacterAfterPlusOrMinus
	Description: True if the given character is not a digit.  
*/
bool isInvalidCharacterAfterPlusOrMinus(string literal, int index) {
    return (!isdigit(literal[index]) && indexHasExponent(literal, index) && 
        indexHasPeriod(literal, index));
}

/* 	Method: isInvalidCharacterAfterExponent
	Description: True if given character should not appear after an exponent.  
*/
bool hasInvalidCharactersAroundExponent(string literal, int indexOfExponent) {
    bool hasInvalidCharactersAroundExponent = false;

    if ((!isdigit(literal[(indexOfExponent - 1)])) || 
        ((!isdigit(literal[(indexOfExponent + 1)]) && 
        (literal[indexOfExponent + 1] != PLUS_SIGN && 
            literal[indexOfExponent + 1] != MINUS_SIGN)))) {
        hasInvalidCharactersAroundExponent = true;
    } else {
        // We've already checked the index after the exponent.
        // To prevent redundancy, we start this check two indicies after the exponent.
        int i = indexOfExponent + 2;  
        while (i < literal.length() && !hasInvalidCharactersAroundExponent) {
            hasInvalidCharactersAroundExponent = isSpecialCharacter(literal[i]);
            i++;
        }
    }
    return hasInvalidCharactersAroundExponent;
}

/* 	Method: isInvalidFirstCharacter 
	Description: True if the first character of a given literal is invalid.  
*/
bool isInvalidFirstCharacter(string literal) {
    return (!isdigit(literal[0]) && !isAllowedFirstCharacter(literal[0]));
}

/* 	Method: indexHasExponent
	Description: True if given index of a literal has an exponent.  
*/
bool indexHasExponent(string literal, int index) {
    return (literal[index] == UPPERCASE_EXPONENT || literal[index] == LOWERCASE_EXPONENT);
}

/* 	Method: isSpecialCharacter
	Description: True if given character is an allowable special character.  
*/
bool isSpecialCharacter(char character) {
    bool isSpecialCharacter = false;
    int i = 0;
    do {
        isSpecialCharacter = character == SPECIAL_CHARACTERS[i];
        i++;
    } while (!isSpecialCharacter && i < NUMBER_OF_SPECIAL_CHARACTERS);  
    return isSpecialCharacter;
}

/*  Method: isAllowFirstCharacter
    Description: True if given character is allowed to be the first character in a literal. 
*/
bool isAllowedFirstCharacter(char character) {
    bool isAllowedFirstCharacter = false;
    int i = 0;

    do {
        isAllowedFirstCharacter = character == ALLOWED_FIRST_CHARACTERS[i];
        i++;
    } while (!isAllowedFirstCharacter && i < NUMBER_ALLOWED_FIRST_CHARACTERS);
    return isAllowedFirstCharacter;
}

