#include <stdexcept>

using namespace std;

class RzymArabException : public runtime_error {
    public : RzymArabException (const string & message);
};
