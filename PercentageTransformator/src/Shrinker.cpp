#include "../include/Shrinker.h"

Shrinker::Shrinker(CSVParser& csvparser, int period)
{
    _period = period;
    for(int j=0; j<csvparser.countColumns(); j++) {
        for(int i=0; i<csvparser.countLines(); i++) {
            for(int i=0;)
        }
    }
}

Shrinker::~Shrinker()
{
}

double Shrinker::mean(vector<double> vec)
{
    double sum = 0.0;
    for(int i=0; i<vec.size(); i++)
        sum += vec.at(i);

    double mean = static_cast<double> (sum) / vec.size();
    return mean;
}

int Shrinker::countLines()
{
    return _countLines;
}

int Shrinker::countColumns()
{
    return _countColumns;
}
