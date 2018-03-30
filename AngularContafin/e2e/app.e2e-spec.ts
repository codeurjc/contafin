import { AngularContafinPage } from './app.po';

describe('angular-contafin App', function() {
  let page: AngularContafinPage;

  beforeEach(() => {
    page = new AngularContafinPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
