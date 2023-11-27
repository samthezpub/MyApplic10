    package edu.itstep.myapplic10;

    import androidx.room.ColumnInfo;
    import androidx.room.Embedded;
    import androidx.room.Entity;
    import androidx.room.PrimaryKey;

    @Entity
    public class Contact {
        @PrimaryKey(autoGenerate = true)
        private int id;

        @ColumnInfo(name = "full_name")
        private String fullName;

        @ColumnInfo(name = "phone_number")
        private String phone;

        @Embedded
        private Address address;

        public Contact() {
        }

        public Contact(String fullName, String phone, Address address) {
            this.fullName = fullName;
            this.phone = phone;
            this.address = address;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Address getAdress() {
            return address;
        }

        public void setAdress(Address adress) {
            this.address = adress;
        }
    }
