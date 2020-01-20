Feature: Send an email with chosen product
  In order to use kaspersky products
  As a generic user
  I want to be able to choose certain product on certain os
  and receive an email with download link of chosen product


  Scenario Outline: Sending an email with chosen product
    Given User is on main page
    And he logs in
    When he goes to download page
    And he goes to <os> Tab
    Then he sees that product <product> has description <description>
    When he send <product> app to himself
    Then he receives email with subject <emailSubject>
    Then he sees message <emailSubject> contains correct link <emailLink>
    Then he logs out

    Examples:
      | os      | product           | description                                                | emailSubject                                  | emailLink                                                  |
      | Windows | Internet Security | Protects you when you surf, socialize & shop online.       | Download link for Kaspersky Internet Security | https://my.kaspersky.com/MyLicenses?startDownload=         |
      | Mac     | Password Manager  | Remembers your passwords for you and stores them securely. | Download links for Kaspersky Password Manager | https://my.kaspersky.com/AlmostDone/Download?applicationId |

