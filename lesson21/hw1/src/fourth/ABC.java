package fourth;

class ABC {
    //к i можно получить доступ:
    //1. создав объект XYZ внутри класса ABC (new XYZ())
    //2. new ABC().new XYZ() в другом классе этого же пакета
    class XYZ {
        int i = 111;
    }
}
