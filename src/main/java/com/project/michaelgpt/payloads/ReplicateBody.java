package com.project.michaelgpt.payloads;


public class ReplicateBody {
  private String id;
  private String model;
  private InputObject input;
  private Urls urls;
  private String logs;
  private String error;
  private String status;
  private String created_at;
  private String version;

  public String getId() {
      return id;
  }

  public String getModel() {
      return model;
  }

  public InputObject getInput() {
      return input;
  }

  public Urls getUrls() {
      return urls;
  }

  public String getLogs() {
      return logs;
  }

  public String getError() {
      return error;
  }

  public String getStatus() {
      return status;
  }

  public String getCreated_at() {
      return created_at;
  }

  public String getVersion(){
    return version;
  }
}
