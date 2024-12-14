package classes;

class Person{
  protected String name;
  protected String email;
  protected String dateOfBirth;

  public Person(String name, String email, String dateOfBirth){
    this.name = name;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
  }

  public String getName(){
    return this.name;
  }

  public String getEmail(){
    return this.email;
  }

  public String getDateOfBirth(){
    return this.dateOfBirth;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public void setDateOfBirth(String dateOfBirth){
    this.dateOfBirth = dateOfBirth;
  } 

  @Override
  public String toString() {
    return String.format("Name: %s\nEmail: %s\nDate of Birth: %s", name, email, dateOfBirth);
  }
}