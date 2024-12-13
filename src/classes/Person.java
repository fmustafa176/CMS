package classes;

class person{
  protected String name;
  protected String email;
  protected String dateOfbirth;

  public person(String name, String email, String dateOfbirth){
    this.name = name;
    this.email = email;
    this.dateOfbirth = dateOfbirth;
  }

  public String getName(){
    return name;
  }

  public String getEmail(){
    return email;
  }

  public String getDateOfBirth(){
    return date_of_birth;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public void setDateOfBirth(String dateOfbirth){
    this.dateOfbirth = dateOfbirth;
  } 

  public String toString(){
    return "Name: " + name + "| Email: " + email + "| Date of Birth: " + date_of_birth;
  }
}