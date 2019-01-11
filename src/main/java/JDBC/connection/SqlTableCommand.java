package JDBC.connection;

import JDBC.exceptions.ExceptionCode;
import JDBC.exceptions.MyException;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SqlTableCommand {

    private List<String> commands;

    private SqlTableCommand(SqlTableCommandBuilder builder) {
        this.commands = builder.commands;
    }

    @Override
    public String toString() {
        if (commands.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder(commands.get(0));
        sb.append(commands.stream().skip(1).collect(Collectors.joining(", "))); // skip(1) means that I skip every element which is before 1 element (first element - it is not an index)
        sb.append(" );");
        return sb.toString();
    }

    public static SqlTableCommandBuilder builder() {
        return new SqlTableCommandBuilder();
    }

    public static class SqlTableCommandBuilder {

        private List<String> commands = new ArrayList<>();

        public SqlTableCommandBuilder table(String name) {
            if (name == null) {
                throw new MyException(ExceptionCode.BUILDER, "TABLE NAME IS NULL");
            }
            if (commands.isEmpty()) {
                commands.add(MessageFormat.format(" create table if not exists {0} ( ", name));
            }
            return this;
        }

        public SqlTableCommandBuilder primaryKey(String name) {
            if (name == null) {
                throw new MyException(ExceptionCode.BUILDER, "PRIMARY KEY IS NULL");
            }
            if (commands.size() == 1) {
                commands.add(MessageFormat.format("{0} integer primary key autoincrement ", name));
            }
            return this;
        }

        public SqlTableCommandBuilder stringColumn(String name, int length, String options) {
            if (name == null) {
                throw new MyException (ExceptionCode.BUILDER, "STRING COLUMN NAME IS NULL");
            }
            if (length < 0){
                throw new MyException (ExceptionCode.BUILDER, "COLUMN LENGTH IS NOT CORRECT");
            }
            if (options == null){
                throw new MyException(ExceptionCode.BUILDER, "OPTIONS EXPRESSION IS NULL");
            }
            if (commands.size() >= 2){
                commands.add(MessageFormat.format("{0} varchar({1}) {2} ", name, length, options));
            }
            return this;

        }

        public SqlTableCommandBuilder intColumn (String name, String options){
            if (name == null){
                throw new MyException (ExceptionCode.BUILDER, "STRING COLUMN NAME IS NULL");
            }
            if (options == null){
                throw new MyException(ExceptionCode.BUILDER, "OPTIONS EXPRESSION IS NULL");
            }
            if(commands.size()>=2){
                commands.add(MessageFormat.format("{0} integer {1} ", name, options));
            }
            return this;
        }

        public SqlTableCommandBuilder decimalColumn (String name, int scale, int precision, String options){
            if (name == null){
                throw new MyException(ExceptionCode.BUILDER, "STRING COLUMN NAME IS NULL" );
            }
            if (scale <= precision){
                throw new MyException(ExceptionCode.BUILDER, "SCALE AND PRECISION NOT CORRECT");
            }
            if (options == null){
                throw new MyException (ExceptionCode.BUILDER, "OPTIONS EXPRESSION IS NULL");
            }
            if (commands.size() >= 2){
                commands.add(MessageFormat.format("{0} decimal ({1},{2}) {3}", name, scale, precision,options));
            }
            return this;
        }

        public SqlTableCommandBuilder column (String name, String type, String options){
            if(name == null){
                throw new MyException(ExceptionCode.BUILDER, "STRING COLUMN NAME IS NULL");
            }
            if (type == null){
                throw new MyException(ExceptionCode.BUILDER, "STRING TYPE IS NULL");
            }
            if (options == null){
                throw new MyException(ExceptionCode.BUILDER, "OPTIONS EXPRESSION IS NULL");
            }
            if (commands.size() >= 2){
                commands.add(MessageFormat.format("{0} {1} {2}", name, type, options));
            }
            return this;
        }

        public SqlTableCommandBuilder foreignKey (String name, String fkTableName, String fkColumn, String options){
            if (name == null){
                throw new MyException(ExceptionCode.BUILDER, "STRING COLUMN IS NULL");
            }
            if(fkTableName == null){
                throw new MyException(ExceptionCode.BUILDER, "FK COLUMN IS NULL");
            }
            if (options == null){
                throw new MyException(ExceptionCode.BUILDER, "OPTIONS EXPRESSION IS NULL");
            }
            if (commands.size()>=2){
                commands.add(MessageFormat.format("foreign key ({0}) references {1} ({2}) {3}", name, fkTableName, fkColumn, options));
            }
            return this;
        }

        public SqlTableCommand build(){
            return new SqlTableCommand(this);
        }

    }
}




