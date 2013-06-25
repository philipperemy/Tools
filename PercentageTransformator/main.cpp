#include <iostream>
#include "include/CSVParser.h"

using namespace std;

int main(int argc, char** argv)
{
    if(argc != 4) {
        cout << "Usage : " << argv[0] << " [CSV FILE] [DELTA_TIME] [MAIN_COLUMN_ID]" << endl;
        return -1;
    }

    int deltaTime = atoi(argv[2]);
    int mainColumnId = atoi(argv[3]);

    CSVParser csvparser(argv[1]);
    csvparser.transformIntoPercentage();
    csvparser.debug();
    csvparser.normalizedDebug();

    /**
        Shrink values day -> hebdo
    **/



    csvparser.printResults();

    return 0;
}
